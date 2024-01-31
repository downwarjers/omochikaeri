package com.kdhr.controller.admin;

import com.kdhr.dto.SetmealDTO;
import com.kdhr.dto.SetmealPageQueryDTO;
import com.kdhr.result.PageResult;
import com.kdhr.result.Result;
import com.kdhr.service.SetmealService;
import com.kdhr.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐相關介面")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 修改套餐
     *
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐 {}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 分頁查詢
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分頁查詢")
    public Result<PageResult> page( SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分頁查詢 {}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 套餐開售與停售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐開售與停售")
    public Result enable(@PathVariable Integer status, Long id) {
        log.info("將套餐id {} 狀態設為 {}", id, status);
        setmealService.enable(status, id);
        return Result.success();
    }

    /**
     * 批量刪除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量刪除套餐")
    public Result deleteBatch(@RequestParam List<Long> ids) {
        log.info("批量刪除套餐 {}", ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 新增套餐
     *
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐 {}", setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /**
     * 根據id查詢套餐
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據id查詢套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根據id查詢套餐 {}", id);
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }
}
