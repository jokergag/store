package com.cy.store.mapper;

import com.cy.store.bean.Product;
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
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> list = productMapper.getHotList();
        for (Product item : list) {
            System.out.println(item);
        }
    }
    @Test
    public void find() {
        Product product = productMapper.findById(10000007);
        System.out.println(product);
    }
}
