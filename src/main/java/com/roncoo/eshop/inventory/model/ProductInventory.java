package com.roncoo.eshop.inventory.model;

import lombok.Data;

/**
 * 库存信息
 *
 * @auther sgyz
 * @create 2018/3/18 0018
 */
@Data
public class ProductInventory {

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 库存数量
     */
    private Long inventoryCnt;

}
