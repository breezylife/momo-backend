package tw.com.momo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.payload.response.myorderResponse;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer> {
	
//	public  List<OrderDetailBean> findByOrder(OrderBean order);
//	public  List<OrderDetailBean> findByOrderid(Integer id);
//	public  Optional<OrderDetailBean> findByProductBean1(ProductBean ProductBean);
	
	@Query(value = 
			"SELECT od.*,"
			+ "pr.name,odd.num,odd.prprice,pr.category,(odd.prprice*odd.num)AS prtotal"
			+ ",pr.id AS prid,pr.cover,spec.spec,spec.stock,odd.id as orderdetailid "
			+ "FROM orderdetail AS odd JOIN orders AS od "
			+ "ON odd.ordersid = od.id JOIN products AS pr "
			+ "ON pr.id = odd.productsid JOIN productspec "
			+ "AS spec ON spec.id = odd.productsid "
			+ "WHERE od.userid = :id",nativeQuery = true)
	public List<myorderResponse> getmyorderdetail(@Param("id") Integer id);
	
	@Query(value = "SELECT od.id,od.shippingadd,od.payment,od.status,od.userid AS buyerid,"
			+ "od.setuptime ,pr.name AS productname,pr.price,odd.num,"
			+ "(odd.num*pr.price)AS prtotal,us.username AS buyername"
			+ " FROM orders AS od JOIN orderdetail AS odd ON od.id = odd.ordersid"
			+ " JOIN products AS pr ON pr.id= odd.productsid "
			+ "JOIN user AS us ON od.userid =us.id WHERE pr.sellerid = 2  ",nativeQuery = true)
	public List<myorderResponse> getmyprwithorder(@Param("id") Integer id);

	
}
