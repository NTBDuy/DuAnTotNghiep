package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    List<Categories> findAllByNameLike(String kw);
}
