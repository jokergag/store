package com.cy.store.controller;

import com.cy.store.bean.District;
import com.cy.store.service.DistrictService;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-28
 */
@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private DistrictService districtService;

    @RequestMapping("")
    public JsonResult<List<District>> find(String parent){
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<>(OK,list);
    }



}
