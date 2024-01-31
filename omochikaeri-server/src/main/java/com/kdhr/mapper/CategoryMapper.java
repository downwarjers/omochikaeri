package com.kdhr.mapper;


import com.github.pagehelper.Page;
import com.kdhr.annotation.AutoFill;
import com.kdhr.dto.CategoryPageQueryDTO;
import com.kdhr.entity.Category;
import com.kdhr.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分類
     *
     * @param category
     */
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * 根據Id修改分類
     *
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    /**
     * 分頁查詢
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根據Id刪除分類
     *
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);


    /**
     * 根據類型查詢分類
     *
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
