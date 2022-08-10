package com.cy.store.mapper;

import com.cy.store.bean.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author joker
 * @create 2022-04-27
 */
@SpringBootTest
public class AddressTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    void testInsert(){
        Address address = new Address();
        address.setUid(11);
        address.setName("jj");
        address.setAddress("长沙理工大学垃圾堆");
        address.setPhone("54188");

        addressMapper.insert(address);
    }
    @Test
    void testCount(){
        Integer count = addressMapper.countByUid(11);
        System.out.println(count);

    }
    @Test
    void test(){
        List<Address> list = addressMapper.findByUid(11);
        list.forEach(System.out::println);
    }
    @Test
    void test1(){
        Address address = addressMapper.findByAid(5);
        System.out.println(address);
    }
    @Test
    void test2(){
        addressMapper.updateNonDefaultByUid(12);
    }
    @Test
    void test3(){
        addressMapper.updateDefaultByAid(5,"admin",new Date());
    }
    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(4);
    }

    @Test
    public void findLastModified() {
        Address result = addressMapper.findLastModified(12);
        System.out.println(result);
    }
}
