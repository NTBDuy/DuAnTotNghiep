package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Product_reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<Product_reviews, Integer> {
}
