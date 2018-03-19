package com.roncoo.eshop.inventory.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求响应
 *
 * @auther sgyz
 * @create 2018/3/19 0019
 */
@Data
@Accessors(chain = true)
public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;

    public Response() {

    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
