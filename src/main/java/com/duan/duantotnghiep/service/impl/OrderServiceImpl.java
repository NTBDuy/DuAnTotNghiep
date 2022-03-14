package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Order_details;
import com.duan.duantotnghiep.entites.Orders;
import com.duan.duantotnghiep.entites.Products;
import com.duan.duantotnghiep.repositories.AccountRepository;
import com.duan.duantotnghiep.repositories.OrderDRepository;
import com.duan.duantotnghiep.repositories.OrderRepository;
import com.duan.duantotnghiep.repositories.ProductRepository;
import com.duan.duantotnghiep.service.OrderService;
import com.duan.duantotnghiep.service.SendMailService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired OrderRepository orderRepository;

	@Autowired AccountRepository accountRepository;
	
	@Autowired OrderDRepository orderDRepository;

	@Autowired SendMailService sendMailService;

	@Autowired ProductRepository productRepository;
	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Orders orders = mapper.convertValue(orderData, Orders.class);
		orderRepository.save(orders);
		TypeReference<List<Order_details>> type = new TypeReference<List<Order_details>>() {};
		List<Order_details> details = mapper.convertValue(orderData.get("orderDetails"),type)
				.stream().peek(d -> d.setOrders(orders)).collect(Collectors.toList());
		orderDRepository.saveAll(details);
		try {
			sendMailAction(orders, "Bạn có một đơn hàng đang chờ xác nhận!", "Chúng tôi sẽ sớm liên hệ với bạn.", "Thông báo đơn hàng đang chờ xác nhận!");
		} catch (Exception ex) {
			System.out.println("ERROR WITH SEND MAIL!");
			ex.printStackTrace();
		}
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
		return orderRepository.findByAccounts_Username(username);
	}

	@Override
	public List<Orders> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	// sendmail
	public void sendMailAction(Orders oReal, String status, String cmt, String notifycation) {
		List<Order_details> list = orderDRepository.findByOrderID(oReal.getID());
		System.out.println(oReal.getID());
		Accounts acc = accountRepository.findById(oReal.getAccounts().getUsername()).get();
		StringBuilder stringBuilder = new StringBuilder();
		int index = 0;
		stringBuilder.append("<h3>Xin chào " + acc.getFullname() + "!</h3>\r\n" + "    <h4>" + status + "</h4>\r\n"
				+ "    <table style=\"border: 1px solid gray;\">\r\n"
				+ "        <tr style=\"width: 100%; border: 1px solid gray;\">\r\n"
				+ "            <th style=\"border: 1px solid gray;\">STT</th>\r\n"
				+ "            <th style=\"border: 1px solid gray;\">Tên sản phẩm</th>\r\n"
				+ "            <th style=\"border: 1px solid gray;\">Số lượng</th>\r\n"
				+ "            <th style=\"border: 1px solid gray;\">Đơn giá</th>\r\n" + "        </tr>");
		for (Order_details oD : list) {
			index++;
			stringBuilder.append("<tr>\r\n" + "            <td style=\"border: 1px solid gray;\">" + index + "</td>\r\n"
					+ "            <td style=\"border: 1px solid gray;\">" + nameP(oD.getProducts().getID()) + "</td>\r\n"
					+ "            <td style=\"border: 1px solid gray;\">" + oD.getQuantity() + "</td>\r\n"
					+ "            <td style=\"border: 1px solid gray;\">" + format(String.valueOf(oD.getPrice()))
					+ "</td>\r\n" + "        </tr>");
		}
		stringBuilder.append("\r\n" + "    </table>\r\n" + "    <h3>Tổng tiền: "
				+ format(String.valueOf(oReal.getAmount())) + "</h3>\r\n" + "    <hr>\r\n" + "    <h5>" + cmt
				+ "</h5>\r\n" + "    <h5>Chúc bạn một ngày tốt lành!</h5>");

		sendMailService.queue(acc.getEmail(), notifycation, stringBuilder.toString());
	}
	public String nameP(Long id) {
		return productRepository.findById(id).get().getName();
	}

	// format currency
	public String format(String number) {
		DecimalFormat formatter = new DecimalFormat("###,###,###.##");

		return formatter.format(Double.valueOf(number)) + " VNĐ";
	}
}
