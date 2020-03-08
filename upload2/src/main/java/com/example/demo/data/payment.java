package com.example.demo.data;




public class payment {

	private double total;
	private String currency;
	private String description;
	private String method;
	private String intent;
	
	
	
	
	public payment() {
	
		// TODO Auto-generated constructor stub
	}
	public payment(double total, String currency, String description, String method, String intent) {
		super();
		this.total = total;
		this.currency = currency;
		this.description = description;
		this.method = method;
		this.intent = intent;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	
	
	
}
