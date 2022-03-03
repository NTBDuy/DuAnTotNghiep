package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.entites.Categories;

import java.util.List;

public interface CategoryService {
    List<Categories> findAll();

    Categories create(Categories c);

    Categories update(Categories c);

    void delete(Long id);
}
