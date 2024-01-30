package com.kdhr.config;

import com.kdhr.properties.AwsOssProperties;
import com.kdhr.utils.AwsOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Slf4j
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AwsOssUtil awsOssUtil(AwsOssProperties awsOssProperties) {
        log.info("產生AWS文件上傳工具 {}", awsOssProperties);

        AwsOssUtil awsOssUtil = AwsOssUtil.builder()
                .accessKeyId(awsOssProperties.getAccessKeyId())
                .accessKeySecret(awsOssProperties.getAccessKeySecret())
                .region(awsOssProperties.getRegion())
                .bucketName(awsOssProperties.getBucketName())
                .build();
        return awsOssUtil;
    }
}