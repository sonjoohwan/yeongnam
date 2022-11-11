package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ManagerDAO;
import vo.Order_detail;

public class OrderDetailService {

	public ArrayList<Order_detail> showDetail(int order_id) {
		Connection con = getConnection(); // import static db.JdbcUtil.*;
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		
		mgrDAO.setConnection(con);
		
		ArrayList<Order_detail> detailList = mgrDAO.showDetail(order_id);
		
		close(con);
		
		return detailList;
	}
}
