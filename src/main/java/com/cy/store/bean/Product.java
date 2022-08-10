package com.cy.store.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-04-30
 */
@Data
public class Product extends BaseEntity implements Serializable {

    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;

}
