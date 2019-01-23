package com.oms.core.permission.dao;

import com.google.common.collect.Lists;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
import com.wah.mybatis.helper.criteria.Criteria;
import com.wah.mybatis.helper.criteria.Restrictions;
import com.oms.commons.consts.CacheName;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.commons.utils.ApplicationContextHolder;
import com.oms.core.permission.dao.mapper.FunctionMapper;
import com.oms.core.permission.entity.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class FunctionDao{

    private Logger logger = LoggerFactory.getLogger(FunctionDao.class);

    @Autowired
    private FunctionMapper mapper;

    @Autowired
    private StringRedisTemplate template;

    public void saveList(List<Function> list){
        try{
            Assert.notEmpty(list, "功能列表不能为空");

            //创建时间
            final Date now = new Date();

            for(Function function : list){
                Assert.notNull(function, "功能信息不能为空");
                Assert.hasText(function.getMethod(), "请求方式不能为空");
                Assert.hasText(function.getApi(), "API不能为空");

                function.setId(IDUtils.uuid32());
                function.setAllocatable(true);
                function.setCookie(true);
                function.setGranted(true);
                function.setCreateTime(now);
            }

            mapper.saveList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateList(List<Function> list){
        try{
            Assert.notEmpty(list, "功能列表不能为空");

            //创建时间
            final Date now = new Date();

            for(Function function : list){
                Assert.notNull(function, "功能信息不能为空");
                Assert.hasText(function.getMethod(), "请求方式不能为空");
                Assert.hasText(function.getApi(), "API不能为空");

                function.setUpdateTime(now);
            }

            mapper.updateList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteList(List<Function> list){
        try{
            Assert.notEmpty(list, "功能列表不能为空");

            for(Function function : list){
                Assert.notNull(function, "功能信息不能为空");
                Assert.hasText(function.getId(), "ID不能为空");
            }

            mapper.deleteList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Function> find(){
        try{
            return mapper.find(null);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Function> current(){
        try{
            List<Function> list = Lists.newArrayList();

            ApplicationContext  context = ApplicationContextHolder.getContext();
            Map<String, Object> beans   = context.getBeansWithAnnotation(RestController.class);

            for(String key : beans.keySet()){
                Object         controller   = beans.get(key);
                Class          clazz        = controller.getClass();
                Method[]       classMethods = clazz.getMethods();
                String[]       classApis    = new String[]{""};
                RequestMapping classMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);

                if(classMapping != null){
                    classApis = classMapping.value();
                }

                for(String classApi : classApis){
                    //遍历所有方法
                    for(Method method : classMethods){
                        RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);

                        if(methodMapping != null){
                            APIDoc          doc            = method.getAnnotation(APIDoc.class);
                            String          description    = doc.description();
                            String[]        methodApis     = methodMapping.value();
                            RequestMethod[] requestMethods = methodMapping.method();

                            //遍历Mapping
                            for(String methodApi : methodApis){
                                //遍历请求方式
                                for(RequestMethod requestMethod : requestMethods){
                                    Function function = new Function();
                                    function.setApi(classApi + methodApi);
                                    function.setMethod(requestMethod.name());
                                    function.setDescription(description);

                                    list.add(function);
                                }
                            }
                        }
                    }
                }
            }

            return list;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Function> findByIds(List<String> ids){
        try{
            Assert.notEmpty(ids, "ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").in(ids));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Boolean needNotCookieByCache(String api, String method){
        try{
            Assert.hasText(api, "API不能为空");
            Assert.hasText(method, "请求方式不能为空");

            return template.opsForSet().isMember(CacheName.PERMISSION_NEED_NOT_COOKIE, api + "_" + method);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Boolean needNotGrantedByCache(String api, String method){
        try{
            Assert.hasText(api, "API不能为空");
            Assert.hasText(method, "请求方式不能为空");

            return template.opsForSet().isMember(CacheName.PERMISSION_NEED_NOT_GRANTED, api + "_" + method);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void cacheNeedNotCookie(){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("cookie").eq(false));

            //查询
            List<Function> list = mapper.find(criteria);

            //添加到缓存
            List<String> caches = Lists.newArrayList();
            for(Function function : list){
               caches.add(function.getApi() + "_" + function.getMethod());
            }

            template.opsForSet().add(CacheName.PERMISSION_NEED_NOT_COOKIE, caches.toArray(new String[caches.size()]));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void cacheNeedNotGrant(){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("granted").eq(false));

            //查询
            List<Function> list = mapper.find(criteria);

            //添加到缓存
            List<String> caches = Lists.newArrayList();
            for(Function function : list){
                caches.add(function.getApi() + "_" + function.getMethod());
            }

            template.opsForSet().add(CacheName.PERMISSION_NEED_NOT_GRANTED, caches.toArray(new String[caches.size()]));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void cacheByRoleId(String roleId, List<Function> functions){
        try{
            Assert.hasText(roleId, "角色ID不能为空");

            if(!functions.isEmpty()){
                List<String> functionNames = Lists.newArrayList();

                for(Function function : functions){
                    functionNames.add(function.getApi() + "_" + function.getMethod());
                }

                template.opsForSet().add(CacheName.ROLE_FUNCTION + roleId, functionNames.toArray(new String[functionNames.size()]));
            }else{
                template.delete(CacheName.ROLE_FUNCTION + roleId);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
