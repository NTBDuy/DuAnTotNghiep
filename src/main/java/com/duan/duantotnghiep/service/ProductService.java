package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Products> findAll();

    List<Products> findAllByNameLike(String name);

    List<Products> findByCategory(Long id);

    List<Products> findByBrands(Long id);

    List<Products> filterProducts(BigDecimal min, BigDecimal max);

    Products findById(Long id);

    Products create(Products p);

    Products update(Products p);

    void delete(Long id);
}
