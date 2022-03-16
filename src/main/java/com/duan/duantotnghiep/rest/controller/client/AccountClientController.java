package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.DTO.RegisterDTO;
import com.duan.duantotnghiep.entites.Response;
import com.duan.duantotnghiep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AccountClientController {
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public Response register(@RequestBody RegisterDTO registerDTO){
        return accountService.register(registerDTO);
    }

    @PostMapping("/forgot/{mail}")
    public Response forgot(@PathVariable("mail") String mail) {
        return accountService.forgot(mail);
    }
}
