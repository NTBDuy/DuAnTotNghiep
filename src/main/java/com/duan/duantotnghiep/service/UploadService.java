package com.duan.duantotnghiep.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface UploadService {

	String save(MultipartFile file, String dir) throws IOException;

}
