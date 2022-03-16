package com.duan.duantotnghiep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    // Truy vấn tới admin home
    @RequestMapping("/")
    public String index(){ return "admin/index";}

    // Truy vấn tới trang quản lý sản phẩm
    @GetMapping("/product")
    public String product(){ return "admin/product"; }

    // Truy vấn tới trang quản lý hãng
    @GetMapping("/brand")
    public String brand(){ return "admin/brand"; }

    // Truy vấn tới trang quản lý danh mục
    @GetMapping("/category")
    public String category(){ return "admin/category"; }

    // Truy vấn tới trang quản lý tài khoản
    @GetMapping("/account")
    public String account(){ return "admin/account"; }

    // Truy vấn tới trang quản lý tài khoản
    @GetMapping("/order")
    public String order(){ return "admin/order"; }

    @GetMapping("/authority")
    public String autho(){ return "admin/authority"; }
}
