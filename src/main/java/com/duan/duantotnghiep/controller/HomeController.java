package com.duan.duantotnghiep.controller;

import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ProductService productService;

    // Truy vấn tới trang chủ
    @GetMapping("/index")
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

    // Truy vấn tới trang hồ sơ cá nhân
    @RequestMapping("/profile")
    public String profile(){ return "client/profile";}

    @RequestMapping("/orderDetail")
    public String orderD(){return "client/order_detail";}

    @RequestMapping("/productDetail/{id}")
    public String productD(Model model, @PathVariable("id") Long id) {
        Products p = productService.findById(id);
        model.addAttribute("items", p);

        return "client/product_detail";
    }
}
