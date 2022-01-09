package tw.com.momo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.ProductRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.ProductDto;
import tw.com.momo.service.ProductRepositoryService;


@RestController
@RequestMapping("/api")
public class ProductRestApiController {
		@Autowired
		ProductRepository productRepository;
		@Autowired
		UserRepository userRepository;
		
		@GetMapping("/product")
		@CrossOrigin
		 public ResponseEntity<?> read() {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserBean user = userRepository.findByUsername(userDetails.getUsername());
			Iterable<ProductBean> products = productRepository.findAllByUserBean(user);
			
			return ResponseEntity.ok(products);
		}
		
//		@GetMapping("/products")
//		 public ResponseEntity<?> reads() {
//			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			UserBean user = userRepository.findByUsername(userDetails.getUsername());
//
//			
//			return ResponseEntity.ok(null);
//		}
//		
		@PostMapping("/product")
		@CrossOrigin
		public ProductBean insert(@RequestBody ProductDto productDto) {
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserBean user = userRepository.findByUsername(userDetails.getUsername());
			
				ProductBean product = new ProductBean(user);
				product.setName(productDto.getName());
				product.setPrice(productDto.getPrice());
				product.setDescription(productDto.getDescription());
				product.setCategory(productDto.getCategory());
				product.setStock(productDto.getStock());

				productRepository.save(product);
			
				return product;
		}
		
		@PutMapping("/products/{id}")
		public ResponseEntity<?> update(@PathVariable("id") Integer id,
				@RequestBody ProductBean bean) {
			return null;
//			ProductBean update = productRepositoryService.update(bean);
//			if(update!=null) {
//				return ResponseEntity.ok(update);
//			} else {
//				return ResponseEntity.notFound().build();
//			}
		}
		
//		@GetMapping("/products/{id}")
//		public ResponseEntity<?> select(@PathVariable("id") Integer id) {
//			ProductBean bean = new ProductBean();
//			bean.setId(id);
//			List<ProductBean> selects = productRepositoryService.select(bean);
//			if(selects!=null && !selects.isEmpty()) {
//				return ResponseEntity.ok(selects.get(0));
//			}
//			return ResponseEntity.notFound().build();
//		}
//		
//		@DeleteMapping("/products/{id}")
//		public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
//			ProductBean bean = new ProductBean();
//			bean.setId(id);
//
//			boolean deleted = productRepositoryService.delete(bean);
//			if(deleted) {
//				return ResponseEntity.noContent().build();
//			} else {
//				return ResponseEntity.notFound().build();
//			}
//		}
//		

}
