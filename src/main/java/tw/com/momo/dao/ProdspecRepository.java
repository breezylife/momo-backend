package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ProdspecBean;


public interface ProdspecRepository extends CrudRepository<ProdspecBean, Integer> {
	
}
