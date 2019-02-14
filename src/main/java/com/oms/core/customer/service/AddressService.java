package com.oms.core.customer.service;

import com.oms.core.customer.entity.Address;

import java.util.List;

public interface AddressService{

    void save(Address address);

    void update(Address address);

    void updateIsDefault(String clientId, String addressId);

    Address getById(String id);

    List<Address> findByClient(String clientId);
}
