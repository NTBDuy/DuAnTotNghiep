package com.duan.duantotnghiep.rest.controller.admin;

import com.duan.duantotnghiep.entites.Order_details;
import com.duan.duantotnghiep.entites.Orders;
import com.duan.duantotnghiep.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin/order")
public class OrderAdminController {
    @Autowired
    OrderService orderService;

    // Lấy tất cả Orders
    @GetMapping()
    public List<Orders> ordersList() {
        return orderService.findAll();
    }

    // Xóa Orders
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    // Lấy OrderDetail theo id Order
    @GetMapping("/detail/{id}")
    public List<Order_details> orderDetailList(@PathVariable("id") Long id) { return orderService.findByOrderId(id);}
}
