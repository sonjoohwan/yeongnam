package svc.manager;

import vo.ProductTBL;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ManagerDAO;

public class ShowProductDetailService {

	public ProductTBL showProductDetail(int product_no) {
		Connection con = getConnection();
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		mgrDAO.setConnection(con);
		
		
		ProductTBL productDetail = mgrDAO.getProductDetail(product_no);
		
		close(con);
		
		return productDetail;
	}

}
