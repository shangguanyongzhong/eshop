package com.roncoo.eshop.inventory.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 测试model类
 */
@Data
@Accessors(chain = true)
public class User {

    private long id;

    private String name;

    private Integer age;

}
