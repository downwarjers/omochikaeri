package com.kdhr.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 套餐總覽
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealOverViewVO implements Serializable {
    // 已開售數量
    private Integer sold;

    // 已停售數量
    private Integer discontinued;
}
