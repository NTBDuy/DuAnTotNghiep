package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.entites.Categories;
import com.duan.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    // Lấy tất cả Categories
    @GetMapping()
    public List<Categories> productList() {
        return categoryService.findAll();
    }

    // Tạo mới Categories
    @PostMapping()
    public Categories create(@RequestBody Categories c) {
        return categoryService.create(c);
    }

    // Sửa Categories
    @PutMapping("{id}")
    public Categories update(@PathVariable("id") String id,
                         @RequestBody Categories c) {
        return categoryService.update(c);
    }

    // Xóa Categories
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
