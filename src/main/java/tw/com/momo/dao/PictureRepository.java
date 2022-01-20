package tw.com.momo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.PictureBean;
import tw.com.momo.domain.ProductBean;

public interface PictureRepository extends CrudRepository<PictureBean, Integer> {
	
	Iterable<PictureBean> findAllByProductBean(Optional<ProductBean> product);

}
