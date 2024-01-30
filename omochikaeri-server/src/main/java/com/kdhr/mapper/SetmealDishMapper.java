package com.kdhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 以Dish_id取的關聯的套餐Id
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);
}
