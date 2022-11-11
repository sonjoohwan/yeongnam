package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import vo.MemberTBL;
import vo.OrderTBL;
import vo.ProductTBL;

public class ProductCartOrderService {

	public boolean orderShoes(int member_code,ArrayList<ProductTBL> productList, int totalMoney) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		// 받아온 member_code,productList , totalMoney를 메서드의 매개변수에 대입
		int insertOrder = productDAO.insertOrderProduct(member_code,productList,totalMoney);
		
		boolean isOrderResult = false;
		if(insertOrder > 0) {
			isOrderResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		 return isOrderResult;
	}
	
	public static OrderTBL userLastOrder(String member_id) {
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		OrderTBL latestOrder = orderDAO.selectLatestOrder(member_id);
		
		close(con);
		return latestOrder;
		
	}

	public static MemberTBL MemberOrder(String member_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		MemberTBL memberOrder = userDAO.selectMemberOrder(member_id);
		close(con);
		
		return memberOrder;
	}

	
}
