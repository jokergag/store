package com.cy.store.mapper;

import com.cy.store.bean.Product;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-30
 */
public interface ProductMapper {

    /**
     * 查询热销商品前四个
     * @return
     */
    List<Product> getHotList();

    Product findById(Integer id);

}
