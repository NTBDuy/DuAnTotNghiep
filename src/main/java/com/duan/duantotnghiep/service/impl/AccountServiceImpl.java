package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.repositories.AccountRepository;
import com.duan.duantotnghiep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Accounts create(Accounts a) {
        return accountRepository.save(a);
    }

    @Override
    public Accounts update(Accounts a) {
        return accountRepository.save(a);
    }

    @Override
    public void delete(String username) {
        accountRepository.deleteById(username);
    }
}
