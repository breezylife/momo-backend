package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ProductsBean;


public interface ProductsRepository extends CrudRepository<ProductsBean, Integer> {
	
}