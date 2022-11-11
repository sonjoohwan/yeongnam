package svc.manager;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.ManagerDAO;
import vo.ProductTBL;

public class ShowProductListService {

	public ArrayList<ProductTBL> allProductList(HttpServletRequest request, int pageNum) {
		Connection con = getConnection();
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		mgrDAO.setConnection(con);
		
		ArrayList<ProductTBL> productList = mgrDAO.allProductList(pageNum);
		
		int maxPage = mgrDAO.maxPage();
		close(con);
		
		request.setAttribute("maxPage", maxPage);
		return productList;
	}
	
}
