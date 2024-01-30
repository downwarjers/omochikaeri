package com.kdhr.service;

import com.kdhr.dto.DishDTO;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    void save(DishDTO dishDTO);

    /**
     * 分頁查詢菜品
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量刪除菜餚
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
