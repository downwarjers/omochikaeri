package com.kdhr.service;

public interface ShopService {
    /**
     * 設定營業狀態
     * @param status
     */
    void setStatus(Integer status);

    /**
     * 獲取營業狀態
     * @return
     */
    Integer getStatus();
}
