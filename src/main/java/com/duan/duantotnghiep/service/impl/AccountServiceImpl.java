package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.repositories.AccountRepository;
import com.duan.duantotnghiep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder pe;

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

    @Override
    public List<Accounts> search(String keyword) {
        return accountRepository.findAllByUsernameLikeOrEmailLikeOrFullnameLikeOrPhoneLike(keyword,keyword,keyword,keyword);
    }

    @Override
    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Accounts create(Accounts a) {
        a.setPassword(pe.encode(a.getPassword()));
        return accountRepository.save(a);
    }

    @Override
    public Accounts update(Accounts a) {
        if (!this.BCRYPT_PATTERN.matcher(a.getPassword()).matches()) {
            a.setPassword(pe.encode(a.getPassword()));
        }
        return accountRepository.save(a);
    }

    @Override
    public void delete(String username) {
        accountRepository.deleteById(username);
    }
}
