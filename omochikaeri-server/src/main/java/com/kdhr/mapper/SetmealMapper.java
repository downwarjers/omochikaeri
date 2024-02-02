package com.kdhr.mapper;

import com.github.pagehelper.Page;
import com.kdhr.annotation.AutoFill;
import com.kdhr.dto.SetmealDTO;
import com.kdhr.dto.SetmealPageQueryDTO;
import com.kdhr.entity.Setmeal;
import com.kdhr.enumeration.OperationType;
import com.kdhr.vo.DishItemVO;
import com.kdhr.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {


    /**
     * 根據分類id查詢套餐的數量
     *
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 修改套餐
     *
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 分頁查詢
     *
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根據id查詢套餐
     *
     * @param id
     * @return
     */
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    /**
     * 批量刪除套餐
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 新增套餐
     *
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 根據id查詢套餐及套餐菜品
     *
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 根據條件查詢套餐
     *
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根據分類Id查詢套餐所含的菜品表
     *
     * @param setmealId
     * @return
     */
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);
}
