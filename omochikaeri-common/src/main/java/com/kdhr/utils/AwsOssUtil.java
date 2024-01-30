package com.kdhr.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Data
@Builder
@AllArgsConstructor
@Slf4j
public class AwsOssUtil {

    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.
                        create(AwsBasicCredentials.create(
                                accessKeyId,
                                accessKeySecret)))
                .build();
    }

    /**
     * 文件上傳
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectName)
                .build();
        PutObjectResponse putObjectResponse = getS3Client().putObject(request, RequestBody.fromBytes(bytes));


        //檔案存取路徑規則 https://BucketName.s3.amazonaws.com/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append("s3")
                .append(".")
                .append(region)
                .append(".")
                .append("amazonaws.com")
                .append("/")
                .append(objectName);

        log.info("檔案上傳到:{}", stringBuilder.toString());

        return stringBuilder.toString();

    }


}