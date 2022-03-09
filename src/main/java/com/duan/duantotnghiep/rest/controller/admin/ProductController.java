package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Categories;
import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/product")
public class ProductController {
    @Autowired ProductService productService;

    // Lấy tất cả Products
    @GetMapping()
    public List<Products> productList() {
        return productService.findAll();
    }

    // Tìm kiếm Pro theo tên
    @GetMapping("/search/{keyword}")
    public  List<Products> findAllByNameLike(@PathVariable("keyword") String keyword) { return  productService.findAllByNameLike("%"+keyword+"%");}

    // Lấy Product theo ID
    @GetMapping("{id}")
    public Products getOne(@PathVariable("id") Long id) {return productService.findById(id);}

    // Tạo mới Product
    @PostMapping()
    public Products create(@RequestBody Products p) {
        return productService.create(p);
    }

    // Sửa Product
    @PutMapping("{id}")
    public Products update(@PathVariable("id") Long id,
                          @RequestBody Products p) {
        return productService.update(p);
    }

    // Xóa Product
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
