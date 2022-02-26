package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, String> {
}
