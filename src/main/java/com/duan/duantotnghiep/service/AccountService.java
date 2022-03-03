package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Brands;

import java.util.List;

public interface AccountService {
    List<Accounts> findAll();

    Accounts create(Accounts a);

    Accounts update(Accounts a);

    void delete(String username);
}
