package tw.com.momo.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import tw.com.momo.domain.ProductBean;

@Service
public interface ProductRepositoryService extends CrudRepository<ProductBean, Integer>{
	
}
