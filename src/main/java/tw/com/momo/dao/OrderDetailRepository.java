package tw.com.momo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.momo.domain.OrderDetailBean;

public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer> {

}
