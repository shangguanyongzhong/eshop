package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 *
 * @auther sgyz
 * @create 2018/3/18 0018
 */
public class RequestProcessorThread implements Callable<Boolean>{

    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }


    @Override
    public Boolean call() throws Exception {
        while(true) {
            break;
        }
        return true;
    }
}
