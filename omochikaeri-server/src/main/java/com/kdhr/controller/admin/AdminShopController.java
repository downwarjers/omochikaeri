package com.kdhr.controller.admin;

import com.kdhr.constant.ShopConstant;
import com.kdhr.result.Result;
import com.kdhr.service.ShopService;
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
    private ShopService shopService;

    /**
     * 設定營業狀態
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("設定營業狀態")
    public Result setStatus(@PathVariable Integer status) {
        shopService.setStatus(status);
        log.info("設定營業狀態 {}", ShopConstant.shopStatusValueOf(status));

        return Result.success();
    }

    /**
     * 獲取營業狀態
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("獲取營業狀態")
    public Result<Integer> getStatus() {
        Integer status = shopService.getStatus();
        log.info("獲取營業狀態 {}", ShopConstant.shopStatusValueOf(status));

        return Result.success(status);
    }

}
