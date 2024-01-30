package com.kdhr.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentVO implements Serializable {
    //隨機字符串
    private String nonceStr;
    //簽名
    private String paySign;
    //時間戳
    private String timeStamp;
    //簽名算法
    private String signType;
    //統一下單接口返回的 prepay_id 參數值
    private String packageStr;
}
