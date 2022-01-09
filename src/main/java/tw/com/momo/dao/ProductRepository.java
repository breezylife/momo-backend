package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;


public interface ProductRepository extends CrudRepository<ProductBean, Integer> {
	Iterable<ProductBean> findAllByUserBean(UserBean user);
}
