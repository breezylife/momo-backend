package tw.com.momo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.momo.dao.OrderRepository;
import tw.com.momo.domain.OrderBean;

@Service
@Transactional
public class OrderRepositoryService {

	@Autowired
	private OrderRepository orderRepository;

	public List<OrderBean> select(Integer id) {

		return null;
	}

	public OrderBean createOrder(OrderBean order) {
		OrderBean result = null;
		if (order != null) {
			return orderRepository.save(order);
		}
		return result;
	}

	
	public Integer getOrderDetail (Integer orderid) {
		Optional<OrderBean> optional = orderRepository.findById(orderid);
		if (optional.isPresent()) {
			OrderBean result = optional.get();
			Integer Orderid = result.getId();
			
			
			return Orderid;
		}
		return null;
	}

	// 0109新增
	// 訂單狀態為1的話變成2,2的話變成3
	// 3的話不會改變
	public OrderBean nextStep(Integer orderid) {
		Optional<OrderBean> optional = orderRepository.findById(orderid);
		if (optional.isPresent()) {
			OrderBean result = optional.get();
			Integer id = result.getStatus();
			if (id <= 2) {
				id++;
			}
			result.setStatus(id);
			return result;
		}
		return null;
	}

	public OrderBean delete(OrderBean order) {

		return null;
	}

}