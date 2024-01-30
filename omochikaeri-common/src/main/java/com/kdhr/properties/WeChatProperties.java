package com.kdhr.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "omochikaeri.wechat")
@Data
public class WeChatProperties {

    private String appid; //小程式的appid
    private String secret; //小程式的秘鑰
    private String mchid; //商家編號
    private String mchSerialNo; //商家API憑證的憑證序號
    private String privateKeyFilePath; //商家私鑰文件
    private String apiV3Key; //憑證解密的金鑰
    private String weChatPayCertFilePath; //平台憑證
    private String notifyUrl; //支付成功的回呼位址
    private String refundNotifyUrl; //退款成功的回呼位址

}