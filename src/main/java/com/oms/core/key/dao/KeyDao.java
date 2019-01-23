package com.oms.core.key.dao;

import com.wah.doraemon.security.exception.DataAccessException;
import com.oms.commons.consts.CacheName;
import com.oms.commons.consts.KeyConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;

@Repository
public class KeyDao{

    private Logger logger = LoggerFactory.getLogger(KeyDao.class);

    @Autowired
    private StringRedisTemplate template;

    public void saveRSAPrivateKey(){
        try{
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource                            resource = resolver.getResource(KeyConfig.RSA_PRIVATE_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            //关闭流
            stream.close();

            //添加到缓存
            template.opsForValue().set(CacheName.RSA_PRIVATE_KEY, sb.toString());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveRSAPublicKey(){
        try{
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource                            resource = resolver.getResource(KeyConfig.RSA_PUBLIC_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            //关闭流
            stream.close();

            //添加到缓存
            template.opsForValue().set(CacheName.RSA_PUBLIC_KEY, sb.toString());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public String getRSAPrivateKey(){
        try{
            String key = template.opsForValue().get(KeyConfig.RSA_PRIVATE_KEY);

            if(StringUtils.isBlank(key)){
                PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource                            resource = resolver.getResource(KeyConfig.RSA_PRIVATE_KEY);

                //读取文件
                FileInputStream stream = new FileInputStream(resource.getFile());
                //缓冲
                byte[] buffer = new byte[4096];
                //结果集
                StringBuffer sb = new StringBuffer();
                //读取长度
                int length = 0;

                while((length = stream.read(buffer)) != -1){
                    sb.append(new String(buffer, 0, length));
                }
                //关闭流
                stream.close();

                key = sb.toString();
                //添加到缓存
                template.opsForValue().set(CacheName.RSA_PRIVATE_KEY, key);
            }

            return key;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public String getRSAPublicKey(){
        try{
            String key = template.opsForValue().get(KeyConfig.RSA_PUBLIC_KEY);

            if(StringUtils.isBlank(key)){
                PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource                            resource = resolver.getResource(KeyConfig.RSA_PUBLIC_KEY);

                //读取文件
                FileInputStream stream = new FileInputStream(resource.getFile());
                //缓冲
                byte[] buffer = new byte[4096];
                //结果集
                StringBuffer sb = new StringBuffer();
                //读取长度
                int length = 0;

                while((length = stream.read(buffer)) != -1){
                    sb.append(new String(buffer, 0, length));
                }
                //关闭流
                stream.close();

                key = sb.toString();
                //添加到缓存
                template.opsForValue().set(CacheName.RSA_PUBLIC_KEY, key);
            }

            return key;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
