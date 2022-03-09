package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {
    List<Brands> findAllByNameLike(String kw);
}
