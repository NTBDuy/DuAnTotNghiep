package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.AccountService;
import com.duan.duantotnghiep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class ShopController {
    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<Products> productsList() {return productService.findAll();}

    @GetMapping("/search/{keyword}")
    public List<Products> productsListByName(@PathVariable("keyword") String keyword) {return productService.findAllByNameLike("%"+keyword+"%");}

    @GetMapping("/byCate/{id}")
    public List<Products> productsListByCate(@PathVariable("id") Long id) {return productService.findByCategory(id);}

    @GetMapping("/byBrand/{id}")
    public List<Products> productsListByBrand(@PathVariable("id") Long id) {return productService.findByBrands(id);}

    @GetMapping("/byPrice/{min}and{max}")
    public List<Products> productsListByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max) {
        return productService.filterProducts(min, max);
    }

    @GetMapping("/getUser")
    public Accounts getAcc(HttpServletRequest request){
        System.out.println("USER is: " + request.getRemoteUser());
        return accountService.findByUsername(request.getRemoteUser());
    }
}
