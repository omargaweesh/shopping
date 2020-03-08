package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class paypalconfig {

	@Value("${paypal.mode}")
	private String mode;
	@Value("${paypal.client.id}")
	private String clientid;
	@Value("${paypal.client.secret}")
	private String clientsecret;
	
	@Bean
	public Map<String,String> paypalsdkconfig(){
		Map<String,String> map=new HashMap<>();
		map.put("mode", mode);
		return map;
	}
	@Bean
	public OAuthTokenCredential OAuthTokenCredential() {
		return new OAuthTokenCredential(clientid,clientsecret,paypalsdkconfig());
	} 
	@Bean
	public APIContext APIContext() throws PayPalRESTException {
		APIContext context=new  APIContext(OAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalsdkconfig());
		return context;
	}

	
	
}
