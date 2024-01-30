package com.kdhr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //開啟註解方式的事務管理
@Slf4j
public class OmochikaeriApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmochikaeriApplication.class, args);
        log.info("server started");
    }
}