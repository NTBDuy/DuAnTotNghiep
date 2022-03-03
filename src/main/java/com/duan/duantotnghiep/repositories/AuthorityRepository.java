package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Long> {
}
