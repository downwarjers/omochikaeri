package com.kdhr.controller.user;

import com.kdhr.constant.ShopConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家管理
 */
@RestController
@RequestMapping("/user/shop")
@Api(tags = "用戶商家相關介面")
@Slf4j
public class UserShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("獲取營業狀態")
    public Result getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS_KEY);
        log.info("獲取營業狀態 {}", ShopConstant.shopStatusValueOf(status));

        return Result.success(status);
    }
}
