package com.cy.store.service;

import com.cy.store.bean.Product;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-30
 */
@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findHotList() {
        try {
            List<Product> list = productService.findHotList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void t(){
        Product product = productService.findById(10000001);
        System.out.println(product);
    }
}
