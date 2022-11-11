package svc.manager;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.xpath.operations.Or;

import dao.ProductDAO;
import vo.OrderTBL;

public class SalesManagementService {

	public ArrayList<OrderTBL> getTotalOrder() {
		Connection con = getConnection();
		
		ProductDAO proDAO = ProductDAO.getInstance();
		
		proDAO.setConnection(con);
		
		ArrayList<OrderTBL> totalOrderList = proDAO.getTotalOrderList();
		
		close(con);
		
		return totalOrderList;
	}
	

}
