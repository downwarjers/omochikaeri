package com.kdhr.service.impl;

import com.kdhr.constant.ShopConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 設定營業狀態
     *
     * @param status
     */
    @Override
    public void setStatus(Integer status) {
        redisTemplate.opsForValue().set(ShopConstant.SHOP_STATUS_KEY, status);
    }

    /**
     * 獲取營業狀態
     *
     * @return
     */
    @Override
    public Integer getStatus() {
        Object object = redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS_KEY);
        if (object != null) {
            Integer status = (Integer) object;
            return status;
        }
        return StatusConstant.DISABLE;
    }
}
