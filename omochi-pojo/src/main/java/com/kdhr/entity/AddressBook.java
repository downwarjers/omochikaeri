package com.kdhr.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 地址簿
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //用戶id
    private Long userId;

    //收貨人
    private String consignee;

    //手機號
    private String phone;

    //性別 0 女 1 男
    private String sex;

    //縣市代碼
    private String cityCode;

    //行政區代碼
    private String districtCode;

    //詳細地址
    private String detail;

    //標籤
    private String label;

    //是否默認 0否 1是
    private Integer isDefault;
}
