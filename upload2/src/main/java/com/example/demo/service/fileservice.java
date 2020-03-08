package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface fileservice {

	
	
	public String loadfile(MultipartFile file);
	
	public Resource downloadResource(String filename);
	
	
	
	
}
