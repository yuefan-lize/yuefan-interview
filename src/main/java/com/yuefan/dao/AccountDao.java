package com.yuefan.dao;

import com.yuefan.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao {

    /**
     * 创建账户
     *
     * @param accountEntity
     * @return true:创建成功, false:创建失败
     */
    Long createAccount(AccountEntity accountEntity);

    AccountEntity selectAccountById(Long accountId);

    int updateAccount(AccountEntity accountEntity);

}
