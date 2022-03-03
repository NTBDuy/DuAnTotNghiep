package com.duan.duantotnghiep.rest.controller.client;

import com.duan.duantotnghiep.entites.Orders;
import com.duan.duantotnghiep.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderClientController {
	@Autowired
	OrderService orderService;
	
	@PostMapping()
	public Orders create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
}
