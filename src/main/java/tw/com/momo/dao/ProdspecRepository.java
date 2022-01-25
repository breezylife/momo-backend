package tw.com.momo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.ProdspecBean;
import tw.com.momo.domain.ProductBean;

@Repository
public interface ProdspecRepository extends CrudRepository<ProdspecBean, Integer> {
	List<ProdspecBean> findAllByProduct(ProductBean product);
//	List<ProdspecBean> findAllByProductBean(ProductBean product);
	Optional<ProdspecBean> findByProductAndSpec(ProductBean product, String spec);
	
}
