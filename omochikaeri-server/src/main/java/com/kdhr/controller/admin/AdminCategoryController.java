package com.kdhr.controller.admin;

import com.kdhr.dto.CategoryDTO;
import com.kdhr.dto.CategoryPageQueryDTO;
import com.kdhr.entity.Category;
import com.kdhr.result.PageResult;
import com.kdhr.result.Result;
import com.kdhr.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分類管理
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "分類相關介面")
@Slf4j
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分類
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分類")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分類：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 分類分頁查詢
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分類分頁查詢")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分頁查詢：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 刪除分類
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("刪除分類")
    public Result<String> deleteById(Long id) {
        log.info("刪除分類：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改分類
     *
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改分類")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 啟用、停用分類
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("啟用停用分類")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        categoryService.enable(status, id);
        return Result.success();
    }

    /**
     * 依類型查詢分類
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("依類型查詢分類")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}