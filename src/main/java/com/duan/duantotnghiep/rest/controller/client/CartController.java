package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cart")
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Products getOne(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
}
