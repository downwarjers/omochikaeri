package com.kdhr.controller.admin;

import com.kdhr.dto.OrdersCancelDTO;
import com.kdhr.dto.OrdersConfirmDTO;
import com.kdhr.dto.OrdersPageQueryDTO;
import com.kdhr.dto.OrdersRejectionDTO;
import com.kdhr.result.PageResult;
import com.kdhr.result.Result;
import com.kdhr.service.OrderService;
import com.kdhr.vo.OrderStatisticsVO;
import com.kdhr.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 訂單管理
 */
@RestController
@RequestMapping("/admin/order")
@Api(tags = "訂單相關介面")
@Slf4j
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 訂單搜尋
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("訂單搜尋")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 各狀態的訂單總量
     *
     * @return
     */
    @GetMapping("/statistics")
    @ApiOperation("各狀態的訂單總量")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 訂單詳細資料
     *
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 接單
     *
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("接單")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 拒單
     *
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("拒單")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 取消訂單
     *
     * @return
     */
    @PutMapping("/cancel")
    @ApiOperation("取消訂單")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 派送訂單
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送訂單")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    /**
     * 完成訂單
     *
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("完成訂單")
    public Result complete(@PathVariable("id") Long id) {
        orderService.complete(id);
        return Result.success();
    }
}
