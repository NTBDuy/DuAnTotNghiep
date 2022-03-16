package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Authorities;

import java.util.List;

public interface AuthorityService {
    List<Authorities> findAll();

    Authorities create(Authorities auth);

    void delete(Long id);
}
