package tw.com.momo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.UserBean;

public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer> {
	
}
