package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
}
