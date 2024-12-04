package com.yuefan.service;

import com.yuefan.entity.AccountEntity;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 创建账户
     *
     * @param account 账户实体
     * @return 是否创建成功
     */
    AccountEntity createAccount(AccountEntity account);


    /**
     * 存款
     *
     * @param accountId  账户ID
     * @param sumOfMoney 存款金额
     * @return
     */
    AccountEntity deposit(Long accountId, BigDecimal sumOfMoney);

    /**
     * 取款
     *
     * @param accountId  账户ID
     * @param depositSum 取款金额
     * @return
     */
    AccountEntity withdrawMoney(Long accountId, BigDecimal depositSum);
}
