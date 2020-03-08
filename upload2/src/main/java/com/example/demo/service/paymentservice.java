package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class paymentservice {

	
	@Autowired
	private APIContext context;
	
	
	public Payment payment(double total, 
			String currency,
			String intent,
			String method,
			String description,
			String successurl,String cancelurl) throws PayPalRESTException {
		Amount amount=new Amount();
		amount.setCurrency(currency);
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(String.format("%.2f", total));
		
		Transaction transaction=new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(description);
		
		List<Transaction> Transactions=new ArrayList<>();
		Transactions.add(transaction);
		
		
		Payer payer=new Payer();
		payer.setPaymentMethod(method);
		
		Payment payment=new Payment();
		payment.setPayer(payer);
		payment.setIntent(intent);
		payment.setTransactions(Transactions);
		
		RedirectUrls redirect=new RedirectUrls();
		redirect.setReturnUrl(successurl);		
		redirect.setCancelUrl(cancelurl);
		payment.setRedirectUrls(redirect);
		
		
		
		
		return payment.create(context);
	}
	
	
	
	public Payment executepayment(String paymentId,String payerId) throws PayPalRESTException {
		Payment payment=new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute=new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(context, paymentExecute);
	}
	
}
