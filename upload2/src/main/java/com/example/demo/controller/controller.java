package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.pathproperty;
import com.example.demo.data.product;
import com.example.demo.service.fileservice;
import com.example.demo.service.product_service;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class controller {

	@Autowired
	product_service empserv;
	@Autowired
	fileservice fserv;
	ObjectMapper om=new ObjectMapper();
	
	pathproperty p; 
	

	
	@RequestMapping("/getone/{id}")
	public Optional<product> getone(@PathVariable("id")Long id) {
		Optional<product> e=empserv.getone(id);
		System.out.println(e+"2222222222222222");
		return e;
	}

	
	@RequestMapping(value="/download/{filename:.+}")
	public ResponseEntity<Resource> download(@PathVariable String filename,HttpServletRequest request)
	{
		Resource re=fserv.downloadResource(filename);
		String contenttype=null;
		
		try {
			request.getServletContext().getMimeType(re.getFile().getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(contenttype==null)
			contenttype="application/octet-stream";
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contenttype))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+re.getFilename())
				.body(re);
	}
	
	
	
}
