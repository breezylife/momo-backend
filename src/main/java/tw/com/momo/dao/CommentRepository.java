package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.CommentBean;

@Repository
public interface CommentRepository extends CrudRepository<CommentBean, Integer>{
	Iterable<CommentBean> findAllByProductsid(Integer id);
}
