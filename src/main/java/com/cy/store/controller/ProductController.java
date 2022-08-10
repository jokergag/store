package com.cy.store.controller;

import com.cy.store.bean.Product;
import com.cy.store.service.ProductService;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-30
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @GetMapping("details/{id}")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return new JsonResult<Product>(OK, data);
    }
}
