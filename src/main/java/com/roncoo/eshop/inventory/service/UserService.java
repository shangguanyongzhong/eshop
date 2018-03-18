package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.User;

/**
 * 查询测试servic
 * @auther sgyz
 * @create 2018/3/17 0017
 */
public interface UserService {
    /**
     * 查询数据库
     * @param id
     * @return
     */
    User findUserById(long id);


    User findCachedUserById(long id);
}
