package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Authorities;
import com.duan.duantotnghiep.entites.Roles;
import com.duan.duantotnghiep.service.AccountService;
import com.duan.duantotnghiep.service.AuthorityService;
import com.duan.duantotnghiep.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/authority")
public class AuthorityController {
    @Autowired
    RoleService roleService;
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;

    @GetMapping("/role")
    public List<Roles> getAllRoles() {return roleService.findAll();}

    @GetMapping("/account")
    public List<Accounts> getAllAcc() {return accountService.findAll();}

    @GetMapping("/authority")
    public List<Authorities> getAllAu() {return authorityService.findAll();}

    @PostMapping
    public Authorities post(@RequestBody Authorities auth) {
        return authorityService.create(auth);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        authorityService.delete(id);
    }
}
