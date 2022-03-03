package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.accounts.username=?1")
    List<Orders> findByUsername(String username);
}
