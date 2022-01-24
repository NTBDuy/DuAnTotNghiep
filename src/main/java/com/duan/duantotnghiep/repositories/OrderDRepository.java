package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDRepository extends JpaRepository<Order_details, Integer>{
}
