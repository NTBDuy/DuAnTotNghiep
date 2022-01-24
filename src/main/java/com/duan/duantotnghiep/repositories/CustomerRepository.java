package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
}
