package com.kdhr.controller.admin;

import com.kdhr.result.Result;
import com.kdhr.utils.AwsOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用介面")
@Slf4j
public class AdminCommonController {


    @Autowired
    private AwsOssUtil awsOssUtil;

    /**
     * 文件上傳
     *
     * @param file
     * @return
     */
//    @PostMapping("/upload")
//    @ApiOperation("文件上傳")
//    public Result<String> upload(MultipartFile file) {
//        log.info("文件上傳 {}", file);
//
//        try {
//            String filename = file.getOriginalFilename();
//            String extension = filename.substring(filename.lastIndexOf("."));
//            String objectName = UUID.randomUUID() + extension;
//
//            String filePath = awsOssUtil.upload(file.getBytes(), objectName);
//
//            return Result.success(filePath);
//        } catch (IOException ex) {
//            log.error("文件上傳失敗 {}", ex);
//        }
//
//        return Result.error(MessageConstant.UPLOAD_FAILED);
//    }

    /**
     * 文件上傳
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上傳")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上傳節流版本 {}", file);
        //TODO 節約流量，後期需還原

        return Result.success("https://omochikaeri-aws-bucket.s3.ap-northeast-1.amazonaws.com/cad6f796-797a-447c-ae42-cd7d09e661d5.jpg");
    }
}

