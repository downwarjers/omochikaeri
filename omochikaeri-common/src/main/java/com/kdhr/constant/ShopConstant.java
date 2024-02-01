package com.kdhr.constant;

public class ShopConstant {

    public static final String SHOP_STATUS_KEY = "SHOP_STATUS";
    public static final String SHOP_CLOSE = "打烊中";
    public static final String SHOP_OPEN = "營業中";

    /**
     * 根據狀態取得是否營業字串
     *
     * @param status
     * @return
     */
    public static String shopStatusValueOf(Integer status) {
        return StatusConstant.ENABLE.equals(status) ? SHOP_OPEN : SHOP_CLOSE;
    }

}