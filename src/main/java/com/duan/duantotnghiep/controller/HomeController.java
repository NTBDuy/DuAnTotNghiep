package com.duan.duantotnghiep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    // Truy vấn tới trang chủ
    @GetMapping()
    public String index(){ return "index";}

    // Truy vấn tới trang cửa hàng
    @RequestMapping("/shop")
    public String shop(){ return "client/shop";}

    // Truy vấn tới trang giỏ hàng
    @RequestMapping("/cart")
    public String cart(){ return "client/cart";}

    // Truy vấn tới trang đặt hàng
    @RequestMapping("/checkout")
    public String checkout(){ return "client/checkout";}
}
