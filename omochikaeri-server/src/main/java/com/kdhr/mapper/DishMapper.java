package com.kdhr.mapper;

import com.github.pagehelper.Page;
import com.kdhr.annotation.AutoFill;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.entity.Dish;
import com.kdhr.enumeration.OperationType;
import com.kdhr.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根據分類id查詢菜餚數量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     *
     * @param dish
     * @return
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分頁查詢菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 查詢菜品
     *
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 批量刪除菜品
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);
}
