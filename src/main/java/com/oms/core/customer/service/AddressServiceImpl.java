package com.oms.core.customer.service;

import com.oms.core.customer.dao.AddressDao;
import com.oms.core.customer.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Override
    public void save(Address address){
        Assert.notNull(address, "客户收货地址信息不能为空");
        Assert.hasText(address.getClientId(), "客户ID不能为空");
        Assert.hasText(address.getCountry(), "地址国家不能为空");
        Assert.hasText(address.getProvince(), "地址省份不能为空");
        Assert.hasText(address.getCity(), "地址城市/区不能为空");
        Assert.hasText(address.getPhone(), "联系方式不能为空");
        Assert.hasText(address.getContact(), "收件人不能为空");

        addressDao.saveOrUpdate(address);
    }

    @Transactional
    @Override
    public void update(Address address){
        Assert.notNull(address, "客户收货地址信息不能为空");
        Assert.hasText(address.getId(), "收货地址ID不能为空");

        addressDao.saveOrUpdate(address);
    }

    @Transactional
    @Override
    public void updateIsDefault(String clientId, String addressId){
        Assert.hasText(clientId, "客户ID不能为空");
        Assert.hasText(addressId, "收货地址ID不能为空");

        //设置所有为非默认
        addressDao.updateIsDefaultByClientId(clientId, false);
        //设置单个为默认
        addressDao.updateIsDefaultById(addressId, true);
    }

    @Override
    public Address getById(String id){
        Assert.hasText(id, "地址信息ID不能为空");

        return addressDao.getById(id);
    }

    @Override
    public List<Address> findByClient(String clientId){
        Assert.hasText(clientId, "客户ID不能为空");

        return addressDao.findByClientId(clientId);
    }
}
