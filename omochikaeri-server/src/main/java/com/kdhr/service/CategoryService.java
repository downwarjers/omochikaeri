package com.kdhr.service;

import com.kdhr.dto.CategoryDTO;
import com.kdhr.dto.CategoryPageQueryDTO;
import com.kdhr.entity.Category;
import com.kdhr.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 新增分類
     *
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分頁查詢
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根據Id刪除分類
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改分類
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 啟用或禁用分類
     *
     * @param status
     * @param id
     */
    void enable(Integer status, Long id);

    /**
     * 根據類型查詢分類
     *
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
