package com.kdhr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kdhr.constant.MessageConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.dto.SetmealDTO;
import com.kdhr.dto.SetmealPageQueryDTO;
import com.kdhr.entity.Dish;
import com.kdhr.entity.Setmeal;
import com.kdhr.entity.SetmealDish;
import com.kdhr.exception.DeletionNotAllowedException;
import com.kdhr.exception.SetmealEnableFailedException;
import com.kdhr.mapper.DishMapper;
import com.kdhr.mapper.SetmealDishMapper;
import com.kdhr.mapper.SetmealMapper;
import com.kdhr.result.PageResult;
import com.kdhr.service.SetmealService;
import com.kdhr.vo.DishItemVO;
import com.kdhr.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private DishMapper dishMapper;

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    @Override
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.update(setmeal);

        setmealDishMapper.deleteBatchBySetmealId(Arrays.asList(setmeal.getId()));

        saveSetmealDish(setmealDTO.getSetmealDishes(), setmealDTO.getId());
    }

    /**
     * 儲存套餐菜品
     *
     * @param setmealDishes
     * @param setmealId
     */
    private void saveSetmealDish(List<SetmealDish> setmealDishes, Long setmealId) {
        if (setmealDishes != null && !setmealDishes.isEmpty()) {
            setmealDishes.forEach(x -> {
                x.setSetmealId(setmealId);
            });
            setmealDishMapper.insert(setmealDishes);
        }
    }

    /**
     * 分頁查詢
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 套餐開售與停售
     *
     * @param status
     * @param id
     */
    @Override
    public void enable(Integer status, Long id) {
        if (StatusConstant.ENABLE.equals(status)) {
            List<Dish> dishList = dishMapper.getBySetmealId(id);
            if (dishList != null && !dishList.isEmpty()) {
                dishList.forEach(dish -> {
                    if (StatusConstant.DISABLE.equals(dish.getStatus())) {
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }

        Setmeal setmeal = Setmeal.builder()
                .status(status)
                .id(id)
                .build();
        setmealMapper.update(setmeal);
    }

    /**
     * 批量刪除套餐
     *
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
            Setmeal setmeal = setmealMapper.getById(id);
            if (StatusConstant.ENABLE.equals(setmeal.getStatus())) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        setmealMapper.deleteBatch(ids);
        setmealDishMapper.deleteBatchBySetmealId(ids);
    }

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Override
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insert(setmeal);

        saveSetmealDish(setmealDTO.getSetmealDishes(), setmeal.getId());
    }

    /**
     * 根據id查詢套餐
     *
     * @param id
     * @return
     */
    @Override
    public SetmealVO getById(Long id) {
        return setmealMapper.getByIdWithDish(id);
    }

    /**
     * 根據條件查詢套餐
     *
     * @param setmeal
     * @return
     */
    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        return setmealMapper.list(setmeal);
    }

    /**
     * 根據分類Id查詢套餐所含的菜品表
     *
     * @param id
     * @return
     */
    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
