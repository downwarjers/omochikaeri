package com.kdhr.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * C端用戶登錄
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;

}
