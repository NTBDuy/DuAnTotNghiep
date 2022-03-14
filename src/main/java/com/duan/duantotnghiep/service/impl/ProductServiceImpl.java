package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.repositories.ProductRepository;
import com.duan.duantotnghiep.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired ProductRepository productRepository;

    @Override
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> findAllByNameLike(String name) {
        return productRepository.findAllByNameLike(name);
    }

    @Override
    public List<Products> findByCategory(Long id) {
        return productRepository.findByCategory(id);
    }

    @Override
    public List<Products> findByBrands(Long id) {
        return productRepository.findByBrands(id);
    }

    @Override
    public List<Products> filterProducts(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(min, max);
    }
    @Override
    public Products findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Products create(Products p) {
        return productRepository.save(p);
    }

    @Override
    public Products update(Products p) {
        return productRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
