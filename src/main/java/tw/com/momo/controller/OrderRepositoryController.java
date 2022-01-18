package tw.com.momo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.OrderDetailRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.OrderDto;
import tw.com.momo.payload.response.myorderResponse;
import tw.com.momo.service.OrderRepositoryService;

@RestController
@RequestMapping(path = "/api")
public class OrderRepositoryController {
	@Autowired
	private UserRepository userRepository;

	

	@Autowired
	private OrderRepositoryService orderRepositoryService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	/*
	 * method:select all order
	 */
	@GetMapping(path = "/orders")
	@CrossOrigin
	public ResponseEntity<?> read() {
		Iterable<OrderDetailBean> orderdetails = orderDetailRepository.findAll();
		return ResponseEntity.ok(orderdetails);
	}
	
	

	/*
	 * 1/14
	 * method:select get order by userid
	 */
	@GetMapping("/myorder")
	@CrossOrigin
	public ResponseEntity<List<myorderResponse>> myorder() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());
		
		List<myorderResponse> myorders = orderDetailRepository.getmyorderdetail(user.getId());
		
		
		return ResponseEntity.ok(myorders);
	}
	
	/*
	 * 1/14
	 * method:select product get order with myproduct
	 */
	@GetMapping("/mycommodity_with_order")
	@CrossOrigin
	public ResponseEntity<List<myorderResponse>> myprwithorder(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());
		List<myorderResponse> myprlistwithorder = orderDetailRepository.getmyprwithorder(user.getId());
		return  ResponseEntity.ok(myprlistwithorder);
	}
	
	

	/*
	 * method:create insert order by user
	 */
	@PostMapping(path = "/order")
	public ResponseEntity<?> createNewOrder(@RequestBody OrderDto order) {
		List<ProductBean> products = order.getProducts();
		// get user
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());

		OrderBean newoder = new OrderBean(user);
		newoder.setTotal(order.getTotal());
		newoder.setPayment(order.getPayment());
		newoder.setShipping(order.getShipping());
		newoder.setStatus(1);
		System.out.println(newoder);
//		newoder.setShippingadd(order.getShippingadd());

		OrderBean result = orderRepositoryService.createOrder(newoder);
		for (ProductBean product : products) {
			OrderDetailBean orderDetail = new OrderDetailBean(newoder, product);
			orderDetail.setNum(product.getStock());
			orderDetail.setPrname(product.getName());
			orderDetail.setPrprice(product.getPrice());
			orderDetailRepository.save(orderDetail);
//			System.out.println(orderDetail);
		}
		if (result != null) {
			URI uri = URI.create("/neworder" + result.getId());
			return ResponseEntity.created(uri).body(result);
		}
		return ResponseEntity.noContent().build();
	}

	/*
	 * method:update update order status by orderid
	 */
	@PatchMapping(path = "/next/{id}")
	public ResponseEntity<?> nextStatus(@PathVariable Integer id, @RequestBody OrderDto order) {
		OrderBean result = orderRepositoryService.nextStep(id);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		}
		return ResponseEntity.notFound().build();
	}
}