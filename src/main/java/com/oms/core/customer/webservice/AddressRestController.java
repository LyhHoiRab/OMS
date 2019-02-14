package com.oms.core.customer.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.customer.entity.Address;
import com.oms.core.customer.service.AddressService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/address")
public class AddressRestController{

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "保存客户收货地址")
    public Responsed save(@RequestBody Address address){
        addressService.save(address);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改客户收货地址")
    public Responsed update(@RequestBody Address address){
        addressService.update(address);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/isDefault", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "设置收货地址为默认地址")
    public Responsed updateIsDefault(String clientId, String addressId){
        addressService.updateIsDefault(clientId, addressId);

        return new Responsed("设置成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据ID查询客户收货地址信息")
    public Responsed<Address> getById(@PathVariable("id") String id){
        Address address = addressService.getById(id);

        return new Responsed<Address>("查询成功", address);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据客户ID查询收货地址信息")
    public Responsed<List<Address>> find(String clientId){
        List<Address> list = addressService.findByClient(clientId);

        return new Responsed<List<Address>>("查询成功", list);
    }
}
