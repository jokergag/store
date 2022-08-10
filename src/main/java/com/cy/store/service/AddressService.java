package com.cy.store.service;

import com.cy.store.bean.Address;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-27
 */
public interface AddressService {

    Integer addAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    void updateDefault(Integer uid,Integer aid,String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid, Integer uid);

}
