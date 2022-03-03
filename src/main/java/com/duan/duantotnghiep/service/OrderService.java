package com.duan.duantotnghiep.service;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Order_details;
import com.duan.duantotnghiep.entites.Orders;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {

	Orders create(JsonNode orderData);

	List<Order_details> findByOrderId(Long id);

	Orders findById(Long id);

	List<Orders> findByUsername(String username);

	List<Orders> findAll();

	void delete(Long id);
}
