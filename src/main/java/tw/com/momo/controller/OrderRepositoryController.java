package tw.com.momo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import tw.com.momo.dao.ProdspecRepository;
import tw.com.momo.dao.ProductRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.ProdspecBean;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.OrderDetailDto;
import tw.com.momo.payload.request.OrderDto;
import tw.com.momo.payload.response.myorderResponse;
import tw.com.momo.service.OrderRepositoryService;

@RestController
@RequestMapping(path = "/api")
public class OrderRepositoryController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepositoryService orderRepositoryService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private ProdspecRepository prodspecRepository;

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
		System.out.println("myorders="+user);
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
	@CrossOrigin
	public ResponseEntity<?> createNewOrder(@RequestBody OrderDto order) {
		List<OrderDetailDto> products = order.getProducts();
		System.out.println("order="+order);
		System.out.println("products="+products);
		// get user
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());

		OrderBean newoder = new OrderBean(user);
		newoder.setTotal(order.getTotal());
		newoder.setPayment(order.getPayment());
		newoder.setShipping(order.getShipping());
		newoder.setStatus(1);
		newoder.setShippingadd(order.getShippingadd());

		OrderBean result = orderRepositoryService.createOrder(newoder);
		for (OrderDetailDto product : products) {
			Optional<ProductBean> optional = productRepository.findById(product.getId());
			ProductBean prod = optional.get();
			Optional<ProdspecBean> op = prodspecRepository.findByProductAndSpec(prod, product.getSpec());
			ProdspecBean prodSpec = op.get();
			prodSpec.setStock(prodSpec.getStock() - product.getNum());
			
			OrderDetailBean orderDetail = new OrderDetailBean(newoder, prod);
			orderDetail.setPrname(product.getName());
			orderDetail.setPrprice(product.getPrice());
			orderDetail.setNum(product.getNum());
			orderDetail.setIscommented(0);
			orderDetailRepository.save(orderDetail);
			System.out.println(orderDetail);
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
	@CrossOrigin
	public ResponseEntity<?> nextStatus(@PathVariable Integer id, @RequestBody OrderDto order) {
		OrderBean result = orderRepositoryService.nextStep(id);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		}
		return ResponseEntity.notFound().build();
	}
}