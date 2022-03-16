package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Authorities;
import com.duan.duantotnghiep.repositories.AuthorityRepository;
import com.duan.duantotnghiep.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImple implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<Authorities> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authorities create(Authorities auth) {
        return authorityRepository.save(auth);
    }

    @Override
    public void delete(Long id) {
        authorityRepository.deleteById(id);
    }
}
