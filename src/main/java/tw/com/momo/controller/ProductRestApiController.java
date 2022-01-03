package tw.com.momo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.ProductRepository;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.service.ProductRepositoryService;


@RestController
@RequestMapping("/api")
public class ProductRestApiController {
		@Autowired
		ProductRepository productRepository;
		@Autowired
		ProductRepositoryService productRepositoryService;
		
		@GetMapping("/products")
		 public void select() {
			Iterable<ProductBean> products = productRepositoryService.findAll();
			System.out.println(products);
		}
		
}
