package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.payment;
import com.example.demo.service.paymentservice;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class paymentController {

	@Autowired
	private paymentservice pay;
	
	private static final String SUCCESS_URL="pay/success";
	private static final String CANCEL_URL="pay/cancel";
	@GetMapping("/paypal")
	public String pay(Model model,HttpSession request) {
		String total=(String) request.getAttribute("total");
		model.addAttribute("total",total);
		return "checkout_pay";
	}
	
	@RequestMapping("/pay")
	public String pay(@ModelAttribute("payment") payment p) throws PayPalRESTException {
		Payment pa=pay.payment(p.getTotal(), p.getCurrency(), p.getIntent()
				, p.getMethod(), p.getDescription(), 
				"http://localhost:8080/"+SUCCESS_URL,
				"http://localhost:8080/"+ CANCEL_URL); 
		for(Links link:pa.getLinks()) {
			if(link.getRel().contains("approval_url")) {
				return "redirect:"+link.getHref();
			}
		}
		return "redirect:/checkout_pay";
		
	}
	
	@GetMapping(value= CANCEL_URL)
	public String cancel() {
		return "cancel";
	}
	
	@GetMapping(value= SUCCESS_URL)
	public String successpay(@RequestParam(name="paymentId")String paymentId
			,@RequestParam("PayerID")String payerID)  {
		
		try {
			 Payment payment = pay.executepayment(paymentId, payerID);
			 System.out.println(payment.toJSON());
			 if(payment.getState().equals("approved")) {
				 return "success";
			 }
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/checkout_pay";
	}
}
