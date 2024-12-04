package com.yuefan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuefan.dao")
public class YuefanApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuefanApplication.class, args);
    }

}
