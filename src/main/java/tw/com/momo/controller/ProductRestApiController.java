package tw.com.momo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.ProductsRepository;

@RestController
@RequestMapping("/api")
public class ProductRestApiController {
		@Autowired
		ProductsRepository productsRepository;
		
//		@GetMapping("")
		
}
