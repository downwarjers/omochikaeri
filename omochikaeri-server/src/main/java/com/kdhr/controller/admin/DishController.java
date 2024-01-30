package com.kdhr.controller.admin;

import com.kdhr.annotation.AutoFill;
import com.kdhr.dto.DishDTO;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.enumeration.OperationType;
import com.kdhr.result.PageResult;
import com.kdhr.result.Result;
import com.kdhr.service.DishService;
import com.kdhr.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相關介面")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品 {}", dishDTO);
        dishService.save(dishDTO);
        return Result.success();
    }

    /**
     * 分頁查詢菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜色分頁查詢")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分頁查詢菜品，參數為:{}" + dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量刪除菜餚
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量刪除菜餚")
    public Result deleteBatch(@RequestParam List<Long> ids){
        log.info("批量刪除菜餚 {}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
