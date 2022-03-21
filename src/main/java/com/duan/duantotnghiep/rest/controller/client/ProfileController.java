package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.DTO.ChangePassDTO;
import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Order_details;
import com.duan.duantotnghiep.entites.Orders;
import com.duan.duantotnghiep.entites.Response;
import com.duan.duantotnghiep.service.AccountService;
import com.duan.duantotnghiep.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/profile")
public class ProfileController {
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;

    @GetMapping("/{username}")
    public Accounts findByUsername(@PathVariable("username") String username) {return accountService.findByUsername(username);}

    @GetMapping("/order/{username}")
    public List<Orders> findOrderByUsername(@PathVariable("username") String username) {return orderService.findByUsername(username);}

    @PostMapping("/changePass")
    public Response changePass(@RequestBody ChangePassDTO changePassDTO){
        return accountService.changePass(changePassDTO);
    }

    // Sửa Accounts
    @PutMapping
    public Accounts update(@RequestBody Accounts a) {
        return accountService.update(a);
    }

    // Lấy OrderDetail theo id Order
    @GetMapping("/order/detail/{id}")
    public List<Order_details> orderDetailList(@PathVariable("id") Long id) { return orderService.findByOrderId(id);}
}
