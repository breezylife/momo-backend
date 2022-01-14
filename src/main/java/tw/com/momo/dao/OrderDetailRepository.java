package tw.com.momo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.payload.response.myoderResponse;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer> {
	
//	public  List<OrderDetailBean> findByOrder(OrderBean order);
//	public  List<OrderDetailBean> findByOrderid(Integer id);
	
	@Query(value = "SELECT od.id, od.shipping,od.payment,od.status,od.shippingadd,"
			+ "pr.name,odd.num ,pr.price,pr.category "
			+ "FROM orderdetail AS odd JOIN orders AS od "
			+ "ON odd.ordersid = od.id JOIN products AS pr "
			+ "ON pr.id = odd.productsid WHERE od.userid = :id",nativeQuery = true)
	public List<myoderResponse> getmyorderdetail(@Param("id") Integer id);
	
	@Query(value = "SELECT od.id,od.shippingadd,od.payment,od.status,od.userid AS buyerid,"
			+ "od.setuptime ,pr.name AS productname,pr.price,odd.num,"
			+ "(odd.num*pr.price)AS prtotal,us.username AS buyername"
			+ " FROM orders AS od JOIN orderdetail AS odd ON od.id = odd.ordersid"
			+ " JOIN products AS pr ON pr.id= odd.productsid "
			+ "JOIN user AS us ON od.userid =us.id WHERE pr.sellerid = 2  ",nativeQuery = true)
	public List<myoderResponse> getmyprwithorder(@Param("id") Integer id);

}
