package tw.com.momo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.UserBean;

@Repository
public interface OrderRepository extends JpaRepository<OrderBean, Integer> {
	public List<OrderBean> findByUserBean(UserBean user);
}