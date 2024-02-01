package com.kdhr.controller.admin;

import com.kdhr.dto.DishDTO;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.entity.Dish;
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
public class AdminDishController {
    @Autowired
    private DishService dishService;

    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品 {}", dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }


    /**
     * 批量刪除菜餚
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量刪除菜餚")
    public Result deleteBatch(@RequestParam List<Long> ids) {
        log.info("批量刪除菜餚 {}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

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
     * 根據id查詢菜品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據id查詢菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根據id查詢菜品 {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 根據分類id查詢菜餚
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根據分類id查詢菜餚")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("分類id查詢菜餚 {}", categoryId);
        List<Dish> dishList = dishService.list(categoryId);
        return Result.success(dishList);
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
     * 菜品開售、停售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @ApiOperation("菜品開售、停售")
    public Result enable(@PathVariable Integer status, Long id) {
        log.info("將菜品id {} 狀態設為 {}", id, status);
        dishService.enable(status, id);
        return Result.success();
    }
}
