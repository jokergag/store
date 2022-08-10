package com.cy.store.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-05-03
 */
@Data
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}
