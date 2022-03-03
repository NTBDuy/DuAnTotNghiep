package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    // Lấy tất cả Accounts
    @GetMapping()
    public List<Accounts> productList() {
        return accountService.findAll();
    }

    // Tạo mới Accounts
    @PostMapping()
    public Accounts create(@RequestBody Accounts a) {
        return accountService.create(a);
    }

    // Sửa Accounts
    @PutMapping("{username}")
    public Accounts update(@PathVariable("username") String username,
                         @RequestBody Accounts a) {
        return accountService.update(a);
    }

    // Xóa Accounts
    @DeleteMapping("{username}")
    public void delete(@PathVariable("username") String username) {
        accountService.delete(username);
    }
}
