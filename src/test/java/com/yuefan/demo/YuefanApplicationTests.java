package com.yuefan.demo;

import com.yuefan.service.AccountService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class YuefanApplicationTests {

    @Resource
    private AccountService accountService;

    @Test
    void contextLoads() {
        // 对一个账户执行一百次扣款和充值操作，验证账户余额是否正确
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> accountService.deposit(1L, new BigDecimal("0.1"))).start();
            new Thread(() -> accountService.withdrawMoney(1L, new BigDecimal("0.1"))).start();

        }
    }

}
