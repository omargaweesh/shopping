package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.data.product;
import com.example.demo.data.product_in_order;
import com.example.demo.service.fileservice;
import com.example.demo.service.order_imp;
import com.example.demo.service.product_service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class firstcontroller {
	@Autowired
	private product_service product;
	
	@Autowired
	fileservice fserv;
	@Autowired 
	order_imp order_product;
	
	ObjectMapper mapper=new ObjectMapper();
	double tt=0;
	
	@RequestMapping("/checkout")
	public String check()
	{
		return "checkout";
	}
	
	@RequestMapping("/welcome")
	public String show(Model model) 
	{
		List<product> em=product.getAll();
		model.addAttribute("prods",em);
		return "map"; 
	}
	@RequestMapping("/delete/")
	public String delete(Long id) {
		product.deleteemp(id);
		return "redirect:/welcome";
	}

	@RequestMapping(value="/update")
	public String update(@RequestParam("id")Long id,@RequestParam("name")String name,
			@RequestParam("price")double price
			,@RequestParam("picpath")MultipartFile file) 
	{
		
		System.out.println(file);
		String url=fserv.loadfile(file);
		String downloaduri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
				.path(url).toUriString();
		product pro=new product();
		pro.setName(name);
		pro.setId(id);
		pro.setPrice(price);
		pro.setPicpath(downloaduri);
		System.out.println(downloaduri);
		
		product.saveEmployee(pro);
		
		return "redirect:/welcome";
		 
	}


	@PostMapping("/send")
	public String send(HttpServletRequest request,@RequestParam("ob")String obJson) throws JsonMappingException, JsonProcessingException {
		@SuppressWarnings("unchecked")
		Map<Long ,product_in_order > list= (Map<Long ,product_in_order >) request.getSession().getAttribute("products");
		String total=(String) request.getSession().getAttribute("total");
		
		if(list==null || total==null) {
				list=new HashMap<>();
				total=null;
				tt=0;
				request.getSession().setAttribute("products", list);
				request.getSession().setAttribute("total", total);
			}
			product pr=mapper.readValue(obJson, product.class);
			Map<Long ,product_in_order > has=new HashMap<>();
			String t= (String) request.getSession().getAttribute("total");
			
			has=(Map<Long, product_in_order>) request.getSession().getAttribute("products");
			product_in_order pro=new product_in_order();
			pro=has.get(pr.getId());
			
			
			if( has.containsKey(pr.getId())) 
			{
				pro.setId(pr.getId());
				pro.setName(pr.getName());
				pro.setPicpath(pr.getPicpath());
				pro.setPrice(pr.getPrice());
				pro.setP_quantity(pro.getP_quantity()+1);
				pro.setTotal(pro.getPrice()*pro.getP_quantity());	
				tt =Double.parseDouble(t);
				tt +=pro.getTotal();
				tt=tt-pro.getPrice();
				has.put(pro.getProduct().getId(), pro);
				list.putAll(has);
				t=String.valueOf(tt);
				total=t;
				
			}
			else{
				Map<Long ,product_in_order > h=new HashMap<>();
				product_in_order p=new product_in_order();
				p.setId(pr.getId());
				p.setName(pr.getName());
				p.setPicpath(pr.getPicpath());
				p.setPrice(pr.getPrice());
				p.setP_quantity(1);
				p.setTotal(pr.getPrice()*p.getP_quantity());
				if(t !=null) {
				tt=Double.parseDouble(t);
				}
				tt +=p.getTotal();
				t=String.valueOf(tt);
				total=t;
				System.out.println(total+"3333333");
				p.setProduct(pr);
				h.put(p.getProduct().getId(), p);
				list.putAll(h);
			}
			
			request.getSession().setAttribute("products", list);
			request.getSession().setAttribute("total", total);
		return "redirect:/index";
	}
	
	
	
	@GetMapping("/index")
	public String home(Model model,HttpSession request) {
		if(request.getAttribute("products")==null)
		{	
			model.addAttribute("products", new ArrayList<>());
			return "checkout";
		}
		else {
		Map<Long ,product_in_order > list=  (Map<Long ,product_in_order >) request.getAttribute("products");
		String total= (String) request.getAttribute("total");
		System.out.println(list.keySet());
		Set s=list.entrySet();
		Iterator it=s.iterator();
		List<product_in_order> li=new ArrayList<>();
		while(it.hasNext()) {
		Map.Entry e=(Map.Entry) it.next();
		product_in_order l= (product_in_order) e.getValue();
		li.add(l);
		}
		System.out.println(total);
		model.addAttribute("total", total);
		model.addAttribute("products", li);
		
		return "checkout";
		}
	}
	 @PostMapping("/invalidate/session")
	    public String destroySession(HttpServletRequest request) {
	        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
		  
		 request.getSession().invalidate();
		  
	        return "redirect:/index";
	    }
	 
	
	
	
	@RequestMapping("/map")
	public String get(Model model ) {
			List<product> em=product.getAll();
			model.addAttribute("emp",em);
			System.out.println(em);
		return "index";
	}
}
