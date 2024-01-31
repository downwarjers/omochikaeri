package com.kdhr.mapper;

import com.kdhr.entity.SetmealDish;
import com.kdhr.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 以Dish_id取的關聯的套餐Id
     *
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 根據批量刪除套餐菜品
     *
     * @param ids
     */
    void deleteBatchBySetmealId(List<Long> ids);

    /**
     * 新增套餐菜品
     *
     * @param setmealDishes
     */
    void insert(List<SetmealDish> setmealDishes);


    /**
     * 以SetmealId取的關聯的套餐菜品
     *
     * @param setmealId
     * @return
     */
    List<SetmealDish> getBySetmealId(Long setmealId);

}
