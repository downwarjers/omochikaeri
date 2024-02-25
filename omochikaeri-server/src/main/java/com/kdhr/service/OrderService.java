package com.kdhr.service;

import com.kdhr.dto.*;
import com.kdhr.result.PageResult;
import com.kdhr.vo.OrderStatisticsVO;
import com.kdhr.vo.OrderSubmitVO;
import com.kdhr.vo.OrderVO;

public interface OrderService {
    /**
     * 用戶下單
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 歷史訂單
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQueryByUser(int pageNum, int pageSize, Integer status);

    /**
     * 查詢訂單詳細資料
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 取消訂單
     * @param id
     */
    void userCancelById(Long id);

    /**
     * 再次下單
     * @param id
     */
    void repetition(Long id);

    /**
     * 訂單搜尋
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各狀態的訂單總量
     * @return
     */
    OrderStatisticsVO statistics();

    /**
     * 接單
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒單
     * @param ordersRejectionDTO
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 取消訂單
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 派送訂單
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成訂單
     * @param id
     */
    void complete(Long id);
}
