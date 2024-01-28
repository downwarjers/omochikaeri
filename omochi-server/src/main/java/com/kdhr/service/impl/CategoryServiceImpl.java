package com.kdhr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kdhr.constant.MessageConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.context.BaseContext;
import com.kdhr.dto.CategoryDTO;
import com.kdhr.dto.CategoryPageQueryDTO;
import com.kdhr.entity.Category;
import com.kdhr.exception.DeletionNotAllowedException;
import com.kdhr.mapper.CategoryMapper;
import com.kdhr.mapper.DishMapper;
import com.kdhr.mapper.SetmealMapper;
import com.kdhr.result.PageResult;
import com.kdhr.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 新增分類
     *
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //屬性拷貝
        BeanUtils.copyProperties(categoryDTO, category);

        //分類狀態預設為停用狀態0
        category.setStatus(StatusConstant.DISABLE);

        //設定創建時間、修改時間、建立人、修改人
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * 分頁查詢
     *
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        //下一條sql進行分頁，自動加入limit關鍵字分頁
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根據id刪除分類
     *
     * @param id
     */
    public void deleteById(Long id) {
        //查詢目前分類是否關聯了菜品，如果關聯了就拋出業務異常
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0) {
            //目前分類下有菜品，不能刪除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        //查詢目前分類是否關聯了套餐，如果關聯了就拋出業務異常
        count = setmealMapper.countByCategoryId(id);
        if (count > 0) {
            //目前分類下有菜品，不能刪除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }

        //刪除分類數據
        categoryMapper.deleteById(id);
    }

    /**
     * 修改分類
     *
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        //設定修改時間、修改人
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);
    }

    /**
     * 啟用、停用分類
     *
     * @param status
     * @param id
     */
    @Override
    public void enable(Integer status, Long id) {

        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }


    /**
     * 依類型查詢分類
     *
     * @param type
     * @return
     */
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}