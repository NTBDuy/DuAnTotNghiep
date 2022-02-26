package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

}
