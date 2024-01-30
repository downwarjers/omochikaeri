package com.kdhr.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {

    private Long id;
    //訂單狀態 1待付款 2待接單 3 已接單 4 派送中 5 已完成 6 已取消 7 退款
    private Integer status;

}
