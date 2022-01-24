package tw.com.momo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.momo.dao.ProductRepository;
import tw.com.momo.domain.ProductBean;

@Service
public class ProductRepositoryService{
	@Autowired
	private ProductRepository productRepository;
	
	public ProductBean postProduct(ProductBean prod) {
		if(prod!=null) {
			return productRepository.save(prod);
		}else {
			return null;
		}
	}
	
	public List<ProductBean> searchProduct(String keyword) {
		if(keyword!=null && keyword.length()!=0) {
			keyword = "%"+keyword+"%";
			return productRepository.findByNameLike(keyword);
		}else {
			 return productRepository.findAll();
		}
	}
	
	//0109新增
	public ProductBean changeState(Integer id) {
		if(id!=null) {
			Optional<ProductBean> optional = productRepository.findById(id);
			if(optional.isPresent()) {
				ProductBean product = optional.get();
				System.out.println("rpaspdfpzxcv"+product);
				if(product.getState().equals(1)) {
					product.setState(0);
				}else if(product.getState().equals(0)) {
					product.setState(1);
				}
				return productRepository.save(product);
			}
		}
		return null;
	}
	
}