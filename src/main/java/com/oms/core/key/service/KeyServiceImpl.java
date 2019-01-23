package com.oms.core.key.service;

import com.oms.core.key.dao.KeyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional(readOnly = true)
public class KeyServiceImpl implements KeyService{

    @Autowired
    private KeyDao keyDao;

//    @PostConstruct
    @Transactional
    @Override
    public void cache(){
        //RSA私钥
        keyDao.saveRSAPrivateKey();
        //RSA公钥
        keyDao.saveRSAPublicKey();
    }
}
