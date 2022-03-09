package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.entites.Categories;
import com.duan.duantotnghiep.repositories.BrandRepository;
import com.duan.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired BrandRepository brandRepository;

    @Override
    public List<Brands> findAllByNameLike(String kw) {
        return brandRepository.findAllByNameLike(kw);
    }

    @Override
    public List<Brands> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brands create(Brands b) {
        return brandRepository.save(b);
    }

    @Override
    public Brands update(Brands b) {
        return brandRepository.save(b);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
