package com.roncoo.eshop.inventory.service.impl;

import com.google.gson.Gson;
import com.roncoo.eshop.inventory.dao.RedisDAO;
import com.roncoo.eshop.inventory.mapper.UserMapper;
import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询测试serviceImpl
 *
 * @auther sgyz
 * @create 2018/3/17 0017
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisDAO redisDAO;

    @Autowired
    private Gson gson;


    @Override
    public User findUserById(long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findCachedUserById(long id) {

        redisDAO.set("cached_user_"+id, "{\"name\": \"lisi\", \"age\":28}");
        String json = redisDAO.get("cached_user_"+id);
        User user = gson.fromJson(json, User.class);
        return user;
    }
}
