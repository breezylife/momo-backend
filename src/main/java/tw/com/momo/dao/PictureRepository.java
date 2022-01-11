package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.PictureBean;

public interface PictureRepository extends CrudRepository<PictureBean, Integer> {
	

}
