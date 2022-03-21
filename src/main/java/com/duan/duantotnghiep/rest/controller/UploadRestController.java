package com.duan.duantotnghiep.rest.controller;

import com.duan.duantotnghiep.DTO.FileData;
import com.duan.duantotnghiep.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class UploadRestController {

//	@PostMapping("/rest/upload/{id}")
	@RequestMapping(value = "/rest/upload/{username}", method = RequestMethod.POST)
	public FileData upload(@PathVariable("username") String id,
						   @PathParam("file") MultipartFile file) throws IOException {
		UUID random = UUID.randomUUID();
		String getFileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).trim();
		String randomNameFile = random + "-" + id + "." + getFileType;
		System.out.println("User is: " + id);
		System.out.println("File type is: " + getFileType);
		System.out.println("Random name is: " + randomNameFile);
		String uploadDir = "uploads/customer";
		saveFile(uploadDir, randomNameFile, file);
		FileData fd = new FileData();
		fd.setFilename(randomNameFile);
		fd.setSize(file.getSize());
		return fd;
	}

	public void saveFile(String uploadDir, String fileName, MultipartFile file) throws IOException{
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);
		}
		try {
			InputStream inputStream = file.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}
}