package com.kdhr.service;

import com.kdhr.dto.DishDTO;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.result.PageResult;
import com.kdhr.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    void save(DishDTO dishDTO);

    /**
     * 分頁查詢菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量刪除菜餚
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 取得菜品及口味
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);
}
