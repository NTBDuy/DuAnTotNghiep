package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {
}
