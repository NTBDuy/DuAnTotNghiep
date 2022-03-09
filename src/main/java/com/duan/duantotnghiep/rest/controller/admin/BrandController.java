package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Brands;
import com.duan.duantotnghiep.entites.Categories;
import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    // Lấy tất cả Brands
    @GetMapping()
    public List<Brands> brandsList() {
        return brandService.findAll();
    }

    // Tìm kiếm Brand theo tên
    @GetMapping("/search/{keyword}")
    public  List<Brands> findAllByNameLike(@PathVariable("keyword") String keyword) { return  brandService.findAllByNameLike("%"+keyword+"%");}

    // Tạo mới Brands
    @PostMapping()
    public Brands create(@RequestBody Brands b) {
        return brandService.create(b);
    }

    // Sửa Brands
    @PutMapping("{id}")
    public Brands update(@PathVariable("id") String id,
                           @RequestBody Brands b) {
        return brandService.update(b);
    }

    // Xóa Brands
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        brandService.delete(id);
    }
}
