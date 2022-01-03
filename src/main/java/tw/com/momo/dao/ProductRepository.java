package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ProductBean;


public interface ProductRepository extends CrudRepository<ProductBean, Integer> {
	
}