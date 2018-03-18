package com.roncoo.eshop.inventory.dao;

/**
 * redis-dao
 *
 * @auther sgyz
 * @create 2018/3/17 0017
 */
public interface RedisDAO {

    void set(String key, String value);

    String get(String key);

}
