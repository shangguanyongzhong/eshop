package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.request.Request;

/**
 * 请求异步执行的service
 *
 * @auther sgyz
 * @create 2018/3/19 0019
 */
    public interface RequestAsyncProcessService {
        void process(Request request);
}
