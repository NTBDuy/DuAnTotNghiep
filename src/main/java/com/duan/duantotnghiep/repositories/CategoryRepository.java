package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {
}