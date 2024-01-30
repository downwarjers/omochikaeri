package com.kdhr.mapper;

import com.kdhr.annotation.AutoFill;
import com.kdhr.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品口味
     * @param flavors
     */
    void insert(List<DishFlavor> flavors);

    void deleteBatch(List<Long> ids);
}
