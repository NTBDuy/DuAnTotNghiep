package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Product_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDRepository extends JpaRepository<Product_details, Integer> {
}
