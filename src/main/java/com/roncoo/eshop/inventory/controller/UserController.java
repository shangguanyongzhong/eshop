package com.roncoo.eshop.inventory.controller;

import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询测试controller
 *
 * @auther sgyz
 * @create 2018/3/17 0017
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(long id){
        return userService.findUserById(id);
    }

    @RequestMapping("/findCachedUserById")
    @ResponseBody
    public User findCachedUserById(long id){
        return userService.findCachedUserById(id);
    }

}
