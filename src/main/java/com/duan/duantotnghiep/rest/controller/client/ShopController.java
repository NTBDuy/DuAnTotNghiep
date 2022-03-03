package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class ShopController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Products> productsList() {return productService.findAll();}
}
