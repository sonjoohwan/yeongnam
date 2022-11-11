package svc.manager;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.ManagerDAO;
import vo.OrderTBL;

public class OrderManagementService {

	public ArrayList<OrderTBL> getOrderList(HttpServletRequest request, int pageNum) {
		Connection con = getConnection(); // import static db.JdbcUtil.*;
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		
		mgrDAO.setConnection(con);
		
		ArrayList<OrderTBL> orderList = mgrDAO.showList(pageNum);
		int maxPage = mgrDAO.maxPage();
		
		close(con);
		
		request.setAttribute("maxPage", maxPage);
		return orderList;
	}

}
