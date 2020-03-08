package com.example.demo.data;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix="file")
public class pathproperty {

	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
}
