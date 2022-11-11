package svc.manager;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ManagerDAO;

public class DeleteProductDetailService {

	public boolean deleteProduct(int product_no) {
		
		boolean deleteProductSuccess = false;
		
		Connection con = getConnection();
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		mgrDAO.setConnection(con);
		
		boolean deleteSuccess = mgrDAO.deleteProduct(product_no);
		
		if(deleteSuccess) {
			deleteProductSuccess = true ;
			commit(con);
		}else {
			rollback(con);
		}
		
		return deleteProductSuccess;
	}
	
}
