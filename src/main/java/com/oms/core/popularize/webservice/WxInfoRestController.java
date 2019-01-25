package com.oms.core.popularize.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.popularize.entity.WxInfo;
import com.oms.core.popularize.service.WxInfoService;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/1.0/wxInfo")
public class WxInfoRestController{

    @Autowired
    private WxInfoService wxInfoService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "新增微信")
    public Responsed save(@RequestBody WxInfo wxInfo){
        wxInfoService.save(wxInfo);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改微信")
    public Responsed update(@RequestBody WxInfo wxInfo){
        wxInfoService.update(wxInfo);

        return new Responsed("修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据ID查询微信信息")
    public Responsed<WxInfo> getById(@PathVariable("id") String id){
        WxInfo wxInfo = wxInfoService.getById(id);

        return new Responsed<WxInfo>("查询成功", wxInfo);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "分页查询微信")
    public Responsed<Page<WxInfo>> page(Long pageNum, Long pageSize, String wxno, String nickname, String phone, String remark){
        Page<WxInfo> page = wxInfoService.page(pageNum, pageSize, wxno, nickname, phone, remark);

        return new Responsed<Page<WxInfo>>("查询成功", page);
    }
}
