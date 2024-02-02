package com.kdhr.controller.user;

import com.kdhr.constant.StatusConstant;
import com.kdhr.entity.Dish;
import com.kdhr.result.Result;
import com.kdhr.service.DishService;
import com.kdhr.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/user/dish")
@Api(tags = "用戶菜品相關介面")
@Slf4j
public class UserDishController {
    @Autowired
    private DishService dishService;
    /**
     * 根據分類id查詢菜餚
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根據分類id查詢菜餚")
    public Result<List<DishVO>> list(Long categoryId) {
        log.info("根據分類id查詢菜餚 {}", categoryId);
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);

        List<DishVO> list = dishService.listWithFlavor(dish);

        return Result.success(list);
    }
}
