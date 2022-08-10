package com.cy.store.mapper;

import com.cy.store.bean.District;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-28
 */
public interface DistrictMapper {

    List<District> findByParent(String parent);

    String findNameByCode(String code);

}
