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
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Arrays;
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


        saveDishFlavor(dishDTO.getFlavors(), dish.getId());
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
            if (StatusConstant.ENABLE.equals(dish.getStatus())) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        });

        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && !setmealIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        dishMapper.deleteBatch(ids);

        dishFlavorMapper.deleteBatchByDishId(ids);
    }

    /**
     * 取得菜品及口味
     *
     * @param id
     * @return
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);

        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);

        dishFlavorMapper.deleteBatchByDishId(Arrays.asList(dishDTO.getId()));

        saveDishFlavor(dishDTO.getFlavors(), dishDTO.getId());

    }

    /**
     * 菜品開售、停售
     *
     * @param status
     * @param id
     */
    @Override
    public void enable(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }

    /**
     * 根據分類id查詢菜餚
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();

        return dishMapper.list(dish);
    }


    /**
     * 重新設定菜品口味
     *
     * @param flavors
     * @param dishId
     */
    private void saveDishFlavor(List<DishFlavor> flavors, Long dishId) {
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(x -> {
                x.setDishId(dishId);
            });
            dishFlavorMapper.insert(flavors);
        }
    }

    /**
     * 根據條件查詢菜品及口味
     *
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d, dishVO);

            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }

}
