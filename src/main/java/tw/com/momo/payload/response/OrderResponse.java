package tw.com.momo.payload.response;

import java.util.List;

import tw.com.momo.domain.OrderBean;
import tw.com.momo.domain.OrderDetailBean;

public class OrderResponse {
	private List<OrderBean> order;
	private List<OrderDetailBean> orderDetail;
}
