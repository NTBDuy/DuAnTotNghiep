package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Order_details;
import com.duan.duantotnghiep.entites.Orders;
import com.duan.duantotnghiep.repositories.OrderDRepository;
import com.duan.duantotnghiep.repositories.OrderRepository;
import com.duan.duantotnghiep.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired OrderRepository orderRepository;
	
	@Autowired OrderDRepository orderDRepository;
	
	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Orders orders = mapper.convertValue(orderData, Orders.class);
		orderRepository.save(orders);
		
		TypeReference<List<Order_details>> type = new TypeReference<List<Order_details>>() {};
		List<Order_details> details = mapper.convertValue(orderData.get("orderDetails"),type)
				.stream().peek(d -> d.setOrders(orders)).collect(Collectors.toList());
		orderDRepository.saveAll(details);
		
		return orders;
	}

	@Override
	public Orders findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order_details> findByOrderId(Long id) {
		return orderDRepository.findByOrderID(id);
	}

	@Override
	public List<Orders> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public List<Orders> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
