package com.yuefan.service.impl;

import com.yuefan.dao.AccountDao;
import com.yuefan.entity.AccountEntity;
import com.yuefan.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Service
//@Slf4j
public class AccountServiceImpl implements AccountService {

    private static final ReentrantLock updateLock = new ReentrantLock();


    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized AccountEntity createAccount(AccountEntity account) {
        if (account.getAccountId() == null) {
            account = new AccountEntity();
            account.setUnfilledVacancies(BigDecimal.valueOf(0.0));
            try {
                accountDao.createAccount(account);
                account.setAccountId(account.getAccountId());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("创建账户失败");
            }
        } else {
            System.out.println("账户已存在，请勿重复创建 ");
        }

//        int i = 1 / 0;

        return account;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountEntity deposit(Long accountId, BigDecimal sumOfMoney) {
        updateLock.lock();
        AccountEntity account;
        try {
            account = accountDao.selectAccountById(accountId);
            if (account == null) {
                throw new RuntimeException("账户不存在");
            }

            account.setUnfilledVacancies(account.getUnfilledVacancies().add(sumOfMoney));
            account.setUpdateTime(LocalDateTime.now());
            accountDao.updateAccount(account);
        } catch (Exception e) {
            throw new RuntimeException("存款失败");
        } finally {
            updateLock.unlock();
        }


//                int i = 1 / 0;
        return account;
    }

    /**
     * 取款
     *
     * @param accountId  账户ID
     * @param depositSum 取款金额
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountEntity withdrawMoney(Long accountId, BigDecimal depositSum) {

        updateLock.lock();
        AccountEntity account;
        try {
            account = accountDao.selectAccountById(accountId);
            if (account == null) {
                throw new RuntimeException("账户不存在");
            }

            if (account.getUnfilledVacancies().compareTo(depositSum) < 0) {
                throw new RuntimeException("余额不足");
            }

            account.setUnfilledVacancies(account.getUnfilledVacancies().subtract(depositSum));
            account.setUpdateTime(LocalDateTime.now());
            accountDao.updateAccount(account);
        } catch (Exception e) {
            throw new RuntimeException("取款失败");
        } finally {
            updateLock.unlock();
        }


//        int i = 1 / 0;
        return account;

    }


}
