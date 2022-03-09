package com.duan.duantotnghiep.repositories;

import com.duan.duantotnghiep.entites.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, String> {
    Optional<Accounts> findByUsernameOrEmail(String username, String email);

    List<Accounts> findAllByUsernameLikeOrEmailLikeOrFullnameLikeOrPhoneLike(String username, String email, String Fullname, String Phone);
}
