package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Brands;

import java.util.List;

public interface BrandService {
    List<Brands> findAll();

    List<Brands> findAllByNameLike(String kw);

    Brands create(Brands b);

    Brands update(Brands b);

    void delete(Long id);
}
