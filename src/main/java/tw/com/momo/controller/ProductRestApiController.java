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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.PictureRepository;
import tw.com.momo.dao.ProdspecRepository;
import tw.com.momo.dao.ProductRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.PictureBean;
import tw.com.momo.domain.ProdspecBean;
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
	@Autowired
	ProductRepositoryService productRepositoryService;
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	ProdspecRepository prodspecRepository;

	@GetMapping("/product")
	@CrossOrigin
	public ResponseEntity<?> products() {
		Iterable<ProductBean> products = productRepository.findAll();

		return ResponseEntity.ok(products);
	}

	@GetMapping("/product/{id}")
	@CrossOrigin
	public ResponseEntity<?> product(@PathVariable Integer id) {
		Optional<ProductBean> product = productRepository.findById(id);

		return ResponseEntity.ok(product);
	}

	@GetMapping("/myproduct")
	@CrossOrigin
	public ResponseEntity<?> myproduct() {
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
	public ResponseEntity<?> insert(@RequestBody ProductDto productDto) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());

		ProductBean product = new ProductBean(user);
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setCategory(productDto.getCategory());
		product.setStock(productDto.getStock());

		product.setStatus(1);
		productRepository.save(product);

		URI uri = URI.create("/product" + product.getId());

		List<String> url = productDto.getUrl();
		for (String pic : url) {
			System.out.println(pic);
			PictureBean pictureBean = new PictureBean(product, pic);
			pictureRepository.save(pictureBean);
		}
		
//		List<ProdspecBean> specs = productDto.getSpecs();
//		for (ProdspecBean spec : specs) {
//			ProdspecBean prodspecBean = new ProdspecBean(product);
//			prodspecBean.setSpec(spec.getSpec());
//			prodspecBean.setStock(spec.getStock());
//			prodspecRepository.save(prodspecBean);
//		}
//				return product;
//				return new ResponseEntity<>(productDto.getName()+"商品已經建立", HttpStatus.OK);
		return ResponseEntity.created(uri).body(product);
	}
//		@PostMapping("/product")
//		@CrossOrigin
//		public ProductBean insert(@RequestBody ProductDto productDto) {
//			
//			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			UserBean user = userRepository.findByUsername(userDetails.getUsername());
//			
//				ProductBean product = new ProductBean(user);
//				product.setName(productDto.getName());
//				product.setPrice(productDto.getPrice());
//				product.setDescription(productDto.getDescription());
//				product.setCategory(productDto.getCategory());
//				product.setStock(productDto.getStock());
//
//				productRepository.save(product);
//			
//				return product;
//		}

	// 關鍵字搜尋
	@GetMapping("/keyword/{keyword}")
	@CrossOrigin
	public ResponseEntity<?> search(@PathVariable String keyword) {

		if (keyword.equals("all")) {
			Iterable<ProductBean> products = productRepository.findAll();

			return ResponseEntity.ok(products);
		} else {

			List<ProductBean> products = productRepositoryService.searchProduct(keyword);
			if (products != null) {
				System.out.println(products);

				return ResponseEntity.ok().body(products);
			}
		}

		return ResponseEntity.notFound().build();
	}

	// 商品下架 0907新增
	@PutMapping("/product/state/{id}")
	@CrossOrigin
	public ResponseEntity<?> removeProd(@PathVariable Integer id) {
		ProductBean remove = productRepositoryService.remove(id);
		if (remove != null) {
			return ResponseEntity.ok().body(remove);
		}

		return ResponseEntity.notFound().build();
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
