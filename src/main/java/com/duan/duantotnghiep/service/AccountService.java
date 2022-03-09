package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Accounts;

import java.util.List;

public interface AccountService {
    List<Accounts> findAll();

    List<Accounts> search(String keyword);

    Accounts create(Accounts a);

    Accounts update(Accounts a);

    void delete(String username);
}
