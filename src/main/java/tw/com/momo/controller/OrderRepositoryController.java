package tw.com.momo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.OrderDetailRepository;
import tw.com.momo.dao.OrderRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.OrderDto;
import tw.com.momo.service.OrderRepositoryService;

@RestController
@RequestMapping(path = "/api")
public class OrderRepositoryController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderRepositoryService orderRepositoryService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	
	@GetMapping(path = "/orders")
	@CrossOrigin
	 public ResponseEntity<?> read() {
		Iterable<OrderDetailBean> orders = orderDetailRepository.findAll();
//		Iterable<OrderBean> orders = orderRepository.findAll();
		
		return ResponseEntity.ok(orders);
	}
	
	
	@PostMapping(path = "/order")
	public ResponseEntity<?> createNewOrder(@RequestBody OrderDto order) {
		List<ProductBean> products = order.getProducts();
		Integer total = 0;
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());
		 
		OrderBean newoder = new OrderBean(user);
		newoder.setTotal(order.getTotal());
		newoder.setPayment(order.getPayment());
		newoder.setShipping(order.getShipping());
		newoder.setStatus(1);
//		newoder.setShippingadd(order.getShippingadd());
		
		OrderBean result = orderRepositoryService.createOrder(newoder);
		for(ProductBean product : products) {
			
			OrderDetailBean orderDetail = new OrderDetailBean(newoder,product);
			
			orderDetailRepository.save(orderDetail);
		}
		
		if(result!=null) {
			URI uri = URI.create("/neworder"+result.getId());
			return ResponseEntity.created(uri).body(result);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/next")
	public void nextStatus(@RequestBody OrderDto order) {
		
		orderRepositoryService.nextStep(null);
	}
	

}