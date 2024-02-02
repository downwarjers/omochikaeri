package com.kdhr.controller.user;

import com.kdhr.constant.StatusConstant;
import com.kdhr.entity.Setmeal;
import com.kdhr.result.Result;
import com.kdhr.service.SetmealService;
import com.kdhr.vo.DishItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/user/setmeal")
@Api(tags = "用戶套餐相關介面")
@Slf4j
public class UserSetmealController {
    @Autowired
    private SetmealService setmealService;


    /**
     * 根據分類Id查詢套餐
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根據分類Id查詢套餐")
    public Result<List<Setmeal>> list(Long categoryId) {
        log.info("根據分類Id查詢套餐 {}", categoryId);
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);

        List<Setmeal> list = setmealService.list(setmeal);
        return Result.success(list);
    }

    /**
     * 根據分類Id查詢套餐所含的菜品表
     *
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    @ApiOperation("根據分類Id查詢套餐所含的菜品表")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        log.info("根據分類Id查詢套餐所含的菜品表 {}", id);
        List<DishItemVO> list = setmealService.getDishItemById(id);
        return Result.success(list);
    }
}
