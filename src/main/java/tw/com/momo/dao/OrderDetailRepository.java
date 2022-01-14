package tw.com.momo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.OrderDetailBean;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer> {
//	public List<OrderDetailBean> findByOrdersid(OrderBean order);
}
