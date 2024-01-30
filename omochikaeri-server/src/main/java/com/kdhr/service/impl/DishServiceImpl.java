package com.kdhr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kdhr.constant.MessageConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.dto.DishDTO;
import com.kdhr.dto.DishPageQueryDTO;
import com.kdhr.entity.Dish;
import com.kdhr.entity.DishFlavor;
import com.kdhr.exception.DeletionNotAllowedException;
import com.kdhr.mapper.DishFlavorMapper;
import com.kdhr.mapper.DishMapper;
import com.kdhr.mapper.SetmealDishMapper;
import com.kdhr.result.PageResult;
import com.kdhr.service.DishService;
import com.kdhr.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    @Transactional
    @Override
    public void save(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();

        if (flavors != null && !flavors.isEmpty()) {
            dishDTO.getFlavors().forEach(x -> {
                x.setDishId(dish.getId());
            });
            dishFlavorMapper.insert(flavors);
        }
    }

    /**
     * 分頁查詢菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量刪除菜餚
     *
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        });

        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && !setmealIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        dishMapper.deleteBatch(ids);

        dishFlavorMapper.deleteBatch(ids);
    }
}
