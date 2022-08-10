package com.cy.store.service.impl;

import com.cy.store.bean.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-30
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.getHotList();
        //将不需要的字段设置为null
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        // 根据参数id调用私有方法执行查询，获取商品数据
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
            // 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("访问的商品数据不存在");
        }
        // 将查询结果中的部分多余属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查询结果
        return product;
    }
}
