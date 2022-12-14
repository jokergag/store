package com.cy.store.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-05-03
 */
@Data
public class CartVO implements Serializable {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

}
