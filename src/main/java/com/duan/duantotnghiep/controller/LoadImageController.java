package com.duan.duantotnghiep.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class LoadImageController {

	@RequestMapping(value = {"getproduct/{photo}","admin/getproduct/{photo}","productDetail/getproduct/{photo}"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getProducts(@PathVariable("photo") String photo) throws IOException {
		if (!photo.equals("") || photo != null) {
			try {
				Path filename = Paths.get("uploads/products/"+photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
			} catch (Exception e) {
			}
		}
		Path filename = Paths.get("uploads/products/notFound.png");
		byte[] buffer = Files.readAllBytes(filename);
		ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
	}

	@RequestMapping(value = {"getcustomer/{photo}","admin/getcustomer/{photo}"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getCustomer(@PathVariable("photo") String photo) throws IOException {
		if (!photo.equals("") || photo != null) {
			try {
				Path filename = Paths.get("uploads/customer/"+photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
			} catch (Exception e) {
			}
		}
		Path filename = Paths.get("uploads/customer/user.png");
		byte[] buffer = Files.readAllBytes(filename);
		ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
	}

	@RequestMapping(value = {"getcustomer/","admin/getcustomer/"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getI() throws IOException {
		Path filename = Paths.get("uploads/customer/user.png");
		byte[] buffer = Files.readAllBytes(filename);
		ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
	}

	@RequestMapping(value = {"getproduct/","admin/getproduct/","productDetail/getproduct/"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getIP() throws IOException {
		Path filename = Paths.get("uploads/products/notFound.png");
		byte[] buffer = Files.readAllBytes(filename);
		ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
		return ResponseEntity.ok().contentLength(buffer.length).body(byteArrayResource);
	}
}
