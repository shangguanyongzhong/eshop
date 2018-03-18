package com.roncoo.eshop.inventory.dao.impl;

import com.roncoo.eshop.inventory.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * redis-dao-impl
 *
 * @auther sgyz
 * @create 2018/3/17 0017
 */
@Repository
public class RedisDAOImpl implements RedisDAO{

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
       return jedisCluster.get(key);
    }

    @Override
    public void delete(String key) {
        jedisCluster.del(key);
    }
}
