package tw.com.momo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.OrderBean;

@Repository
public interface OrderRepository extends JpaRepository<OrderBean, Integer> {
	
	

}