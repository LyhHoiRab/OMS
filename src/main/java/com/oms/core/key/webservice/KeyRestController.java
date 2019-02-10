package com.oms.core.key.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.key.service.KeyService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/key")
public class KeyRestController{

    @Autowired
    private KeyService keyService;

    @RequestMapping(value = "/rsa/public", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询RSA公钥信息")
    public Responsed<String> getRSAPublicKey(){
        String key = keyService.getRSAPublicKey();

        return new Responsed<String>("查询成功", key);
    }
}
