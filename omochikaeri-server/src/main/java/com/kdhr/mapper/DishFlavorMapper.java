package com.kdhr.mapper;

import com.kdhr.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品口味
     *
     * @param flavors
     */
    void insert(List<DishFlavor> flavors);

    /**
     * 刪除菜品口味
     * @param ids
     */
    void deleteBatchByDishId(List<Long> ids);

    /**
     * 查詢菜品為
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
