package com.kdhr.controller.admin;

import com.kdhr.constant.ShopConstant;
import com.kdhr.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.http.HttpStatusCode;

/**
 * 商家管理
 */
@RestController
@RequestMapping("/admin/shop")
@Api(tags = "商家相關介面")
@Slf4j
public class AdminShopController {
    @Autowired
    private RedisTemplate redisTemplate;


    @PutMapping("/{status}")
    @ApiOperation("設定營業狀態")
    public Result setStatus(@PathVariable Integer status) {
        log.info("設定營業狀態 {}", ShopConstant.shopStatusValueOf(status));

        redisTemplate.opsForValue().set(ShopConstant.SHOP_STATUS_KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("獲取營業狀態")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS_KEY);
        log.info("獲取營業狀態 {}", ShopConstant.shopStatusValueOf(status));

        return Result.success(status);
    }

}
