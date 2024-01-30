package com.kdhr.controller.admin;

import com.kdhr.constant.MessageConstant;
import com.kdhr.result.Result;
import com.kdhr.utils.AwsOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用介面")
@Slf4j
public class CommonController {


    @Autowired
    private AwsOssUtil awsOssUtil;

    /**
     * 文件上傳
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上傳")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上傳 {}", file);

        try {
            String filename = file.getOriginalFilename();
            String extension = filename.substring(filename.lastIndexOf("."));
            String objectName = UUID.randomUUID() + extension;

            String filePath = awsOssUtil.upload(file.getBytes(), objectName);

            return Result.success(filePath);
        } catch (IOException ex) {
            log.error("文件上傳失敗 {}", ex);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}

