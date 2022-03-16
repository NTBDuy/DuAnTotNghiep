package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.DTO.ChangePassDTO;
import com.duan.duantotnghiep.DTO.RegisterDTO;
import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Response;

import java.util.List;

public interface AccountService {
    List<Accounts> findAll();

    List<Accounts> search(String keyword);

    Accounts findByUsername(String username);

    Response changePass(ChangePassDTO changePassDTO);

    Response forgot(String mail);

    Response register(RegisterDTO registerDTO);

    Response create(Accounts a);

    Accounts update(Accounts a);

    void delete(String username);
}
