package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Roles;
import com.duan.duantotnghiep.repositories.RoleRepository;
import com.duan.duantotnghiep.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }
}
