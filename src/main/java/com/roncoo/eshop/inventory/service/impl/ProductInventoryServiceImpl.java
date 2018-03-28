package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.dao.RedisDAO;
import com.roncoo.eshop.inventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品库存Service实现类
 *
 * @auther sgyz
 * @create 2018/3/18 0018
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;

    @Autowired
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
        System.out.println("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        redisDAO.delete("product:inventory:" + productInventory.getProductId());
        System.out.println("===========日志===========: 已删除redis中的缓存，key=" + "product:inventory:" + productInventory.getProductId());
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        redisDAO.set("product:inventory:" + productInventory.getProductId(), String.valueOf(productInventory.getInventoryCnt()));
        System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + "product:inventory:" + productInventory.getProductId());
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;

        String key = "product:inventory:" + productId;
        String result = redisDAO.get(key);
        if(result != null && !"".equals(result)){
            try {
                inventoryCnt = Long.valueOf(result);
                return  new ProductInventory(productId, inventoryCnt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
