package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.pathproperty;


@Service
public class serviceimp implements fileservice {

	private Path direc;
	
	@Autowired
	public serviceimp(pathproperty path)
	{
		direc=Paths.get(path.getDirection()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(direc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public String loadfile(MultipartFile file) {
		
		if((file.getOriginalFilename().endsWith(".png")||
				file.getOriginalFilename().endsWith(".jpg")))
		{
			File f=new File("F://ppp//"+file.getOriginalFilename());
			
			try {
				FileOutputStream ot=new FileOutputStream(f);
				ot.write(file.getBytes());
				ot.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		String fi=StringUtils.cleanPath(file.getOriginalFilename());
		
		Path pp=direc.resolve(fi);
		try {
			Files.copy(file.getInputStream(), pp, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
		
		
		
		return fi;
	}

	@Override
	public Resource downloadResource(String filename) {
		
		Path p=direc.resolve(filename).normalize();
		try {
			Resource r=new UrlResource(p.toUri());
			if(r.exists())
				return r;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}

}
