package com.cy.store.service;

import com.cy.store.bean.Product;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-30
 */
public interface ProductService {

    List<Product> findHotList();

    Product findById(Integer id);

}
