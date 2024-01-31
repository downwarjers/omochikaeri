package com.kdhr.service;

import com.kdhr.dto.SetmealDTO;
import com.kdhr.dto.SetmealPageQueryDTO;
import com.kdhr.result.PageResult;
import com.kdhr.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 分頁查詢
     *
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 套餐開售與停售
     *
     * @param status
     * @param id
     */
    void enable(Integer status, Long id);

    /**
     * 批量刪除套餐
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 根據id查詢套餐
     *
     * @param id
     * @return
     */
    SetmealVO getById(Long id);
}
