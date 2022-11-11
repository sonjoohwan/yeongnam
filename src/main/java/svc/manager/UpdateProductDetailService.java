package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ManagerDAO;
import vo.ProductTBL;

public class UpdateProductDetailService {

	public boolean updateProduct(ProductTBL protbl) {
		Connection con = getConnection();
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		mgrDAO.setConnection(con);
		
		boolean updateProductSuccess = mgrDAO.updateProduct(protbl);
		
		if(updateProductSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return updateProductSuccess;
	}

}
