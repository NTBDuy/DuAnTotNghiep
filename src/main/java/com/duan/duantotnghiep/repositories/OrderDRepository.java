package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDRepository extends JpaRepository<Order_details, Long>{
    @Query("SELECT o FROM Order_details o WHERE o.orders.id=?1")
    List<Order_details> findByOrderID(Long id);
}
