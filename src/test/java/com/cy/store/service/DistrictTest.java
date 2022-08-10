package com.cy.store.service;

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
    private DistrictService districtService;

    @Test
    void t(){
        List<District> l = districtService.getByParent("86");
        l.forEach(System.out::println);
    }

}
