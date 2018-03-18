package com.roncoo.eshop.inventory.mapper;

import com.roncoo.eshop.inventory.model.User;

/**
 * 测试用户的mapper接口
 */
public interface UserMapper {

    /**
     * 测试用户信息
     * @param id
     * @return
     */
     User findUserById(long id);

}
