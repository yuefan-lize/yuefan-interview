package com.yuefan.controller;

import com.yuefan.entity.AccountEntity;
import com.yuefan.service.AccountService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;


@Controller
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Resource
    private AccountService accountService;

    @PostMapping("/createAccount") //http://localhost/goods?gid=xxx
    @ResponseBody
    public Object createAccount(@RequestBody AccountEntity accountEntity) {
        AccountEntity account = accountService.createAccount(accountEntity);

        if (account != null) {
            return "账户创建成功，id为：" + account.getAccountId();
        } else {
            return "账户创建失败";
        }
    }


    @PostMapping("/deposit") //http://localhost/goods?gid=xxx
    @ResponseBody
    public Object deposit(@RequestBody Map<String, Object> params) {
        Object accountId = params.get("accountId");
        if (accountId == null) {
            return "账户不能为空";
        }

        Object sumOfMoney = params.get("sumOfMoney");
        BigDecimal depositSum = new BigDecimal(String.valueOf(sumOfMoney));
        if (depositSum.compareTo(BigDecimal.ZERO) <= 0) {
            return "存款金额必须大于0";
        }


        AccountEntity account = accountService.deposit(Long.parseLong(accountId.toString()), depositSum);

        if (account != null) {
            return account.getAccountId() + "账户存款成功，当前余额为：" + account.getUnfilledVacancies();
        } else {
            return account.getAccountId() + "账户存款失败，当前余额为：" + account.getUnfilledVacancies();
        }
    }


    @PostMapping("/withdrawMoney") //http://localhost/goods?gid=xxx
    @ResponseBody
    public Object withdrawMoney(@RequestBody Map<String, Object> params) {
        Object accountId = params.get("accountId");
        if (accountId == null) {
            return "账户不能为空";
        }

        Object sumOfMoney = params.get("sumOfMoney");
        BigDecimal depositSum = new BigDecimal(String.valueOf(sumOfMoney));
        if (depositSum.compareTo(BigDecimal.ZERO) == 0) {
            return "取款金额必须大于0";
        }


        AccountEntity account = accountService.withdrawMoney(Long.parseLong(accountId.toString()), depositSum);

        if (account != null) {
            return "账户" + account.getAccountId() + "取款成功，当前余额为：" + account.getUnfilledVacancies();
        } else {
            return "账户" + account.getAccountId() + "取款失败，当前余额为：" + account.getUnfilledVacancies();
        }
    }
}
