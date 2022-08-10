package com.cy.store.service;

import com.cy.store.bean.Address;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-27
 */
@SpringBootTest
public class AddressTest {

    @Autowired
    AddressService addressService;

    @Test
    void testinsert(){
        Address address = new Address();
        address.setUid(11);
        address.setName("tbw001");
        address.setAddress("长沙理工大学保安亭");
        address.setPhone("888888");

        addressService.addAddress(11,"admin",address);
    }
    @Test
    void test(){
        List<Address> list = addressService.getByUid(12);
        list.forEach(System.out::println);
    }
    @Test
    void test1(){
        addressService.updateDefault(11,2,"admin");
    }
    @Test
    public void delete() {
        try {
            Integer aid = 6;
            Integer uid = 12;
            String username = "admin";
            addressService.delete(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
