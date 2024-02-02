package com.kdhr.controller.user;

import com.kdhr.entity.Category;
import com.kdhr.result.Result;
import com.kdhr.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分類管理
 */
@RestController
@RequestMapping("/user/category")
@Api(tags ="用戶分類相關介面")
@Slf4j
public class UserCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查詢分類
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查詢分類")
    public Result<List<Category>> list(Integer type) {
        log.info("查詢分類：{}", type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
