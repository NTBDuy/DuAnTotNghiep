package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Products> findAll();

    List<Products> findAllByNameLike(String name);

    List<Products> findByCategory(Long id);

    List<Products> findByBrands(Long id);

    Products findById(Long id);

    Products create(Products p);

    Products update(Products p);

    void delete(Long id);
}
