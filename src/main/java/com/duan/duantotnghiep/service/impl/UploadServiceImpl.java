package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadServiceImpl implements UploadService {
	@Override
	public String save(MultipartFile file, String dir) throws IOException {
		Path path = Paths.get(dir);
		InputStream inputStream = file.getInputStream();
		Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		return file.getOriginalFilename();

//		File dir = new File(app.getRealPath("/assets/images/"));
//		System.out.println(dir);
//		if(!dir.exists()) {
//			dir.mkdirs();
//		}
//		String s = System.currentTimeMillis()+file.getOriginalFilename();
//		String name = Integer.toHexString(s.hashCode())+ s.substring(s.lastIndexOf("."));
//		try {
//			File saveFile = new File(dir,name);
//			file.transferTo(saveFile);
//			System.out.println(saveFile.getAbsolutePath());
//			return saveFile;
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			throw new RuntimeException(e);
//		}
	
	}

}
