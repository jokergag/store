package com.cy.store.mapper;

import com.cy.store.bean.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-28
 */
@SpringBootTest
public class DistrictTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    void testFindByParent(){
        List<District> list = districtMapper.findByParent("210100");
        list.forEach(System.out::println);
    }
    @Test
    void test(){
        String name = districtMapper.findNameByCode("210100");
        System.out.println(name);
    }
}
