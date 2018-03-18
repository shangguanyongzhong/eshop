package com.roncoo.eshop.inventory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化监听器
 *
 * @auther sgyz
 * @create 2018/3/18 0018
 */
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("系统初始化线程池");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
