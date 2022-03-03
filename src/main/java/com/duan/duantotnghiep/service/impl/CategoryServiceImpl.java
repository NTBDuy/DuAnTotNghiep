package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.entites.Categories;
import com.duan.duantotnghiep.repositories.CategoryRepository;
import com.duan.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories create(Categories c) {
        return categoryRepository.save(c);
    }

    @Override
    public Categories update(Categories c) {
        return categoryRepository.save(c);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
