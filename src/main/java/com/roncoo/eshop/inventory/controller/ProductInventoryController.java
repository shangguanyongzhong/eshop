package com.roncoo.eshop.inventory.controller;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.roncoo.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import com.roncoo.eshop.inventory.service.RequestAsyncProcessService;
import com.roncoo.eshop.inventory.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品库存Controller
 *
 * @auther sgyz
 * @create 2018/3/19 0019
 */
@Controller
public class ProductInventoryController {
    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * 更新商品库存
     */
    @RequestMapping("/updateProductInventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventory productInventory){
        System.out.println("===========日志===========: 接收到更新商品库存的请求，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
        Response response = null;
        try{
            Request request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }

    /**
     * 获取商品库存
     */
    @RequestMapping("/getProductInventory")
    @ResponseBody
    public ProductInventory getProductInventory(Integer productId) {
        System.out.println("===========日志===========: 接收到一个商品库存的读请求，商品id=" + productId);
        ProductInventory productInventory = null;
        try {

            Request request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService, false);
            requestAsyncProcessService.process(request);
            // 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
            // 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;
            // 等待超过200ms没有从缓存中获取到结果
            while (true){
                if(waitTime > 2500){
                    break;
                }
                // 尝试去redis中读取一次商品库存的缓存数据
                productInventory = productInventoryService.getProductInventoryCache(productId);
                // 如果读取到了结果，那么就返回
                if(productInventory != null){
                    return productInventory;
                }
                // 如果没有读取到结果，那么等待一段时间
                else{
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }
            // 直接尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if(productInventory != null){
                request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService, true);
                requestAsyncProcessService.process(request);
                return productInventory;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ProductInventory(productId, -1L);
    }
}
