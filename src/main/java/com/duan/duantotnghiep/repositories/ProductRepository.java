package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByNameLike(String name);

    @Query("SELECT p FROM Products p WHERE p.categories.ID=?1")
    List<Products> findByCategory(Long id);

    @Query("SELECT p FROM Products p WHERE p.brands.ID=?1")
    List<Products> findByBrands(Long id);
}
