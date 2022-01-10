package tw.com.momo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;


public interface ProductRepository extends CrudRepository<ProductBean, Integer> {
	Iterable<ProductBean> findAllByUserBean(UserBean user);

	public List<ProductBean> findByNameLike(String name);
	public List<ProductBean> findAll();
}
