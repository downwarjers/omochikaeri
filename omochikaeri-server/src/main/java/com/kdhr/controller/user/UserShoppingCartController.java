package com.kdhr.controller.user;

import com.kdhr.dto.ShoppingCartDTO;
import com.kdhr.entity.ShoppingCart;
import com.kdhr.result.Result;
import com.kdhr.service.ShoppingCartService;
import com.kdhr.service.impl.ShoppingCartServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 購物車管理
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Api(tags = "購物車相關介面")
@Slf4j
public class UserShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 新增購物車
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增購物車")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加購物車 {}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);

        return Result.success();
    }

    /**
     * 查看購物車
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看購物車")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    /**
     * 清空購物車
     *
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空購物車")
    public Result clean() {
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    /**
     * 刪除購物車中一個商品
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("刪除購物車中一個商品")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("刪除購物車中一個商品，商品：{}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }

}
