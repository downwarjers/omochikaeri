package com.kdhr.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "omochikaeri.awsoss")
@Data
public class AwsOssProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String region;
    private String bucketName;

}
