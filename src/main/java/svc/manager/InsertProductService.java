package svc.manager;

import java.sql.Connection;

import dao.ManagerDAO;
import vo.ProductTBL;

import static db.JdbcUtil.*;

public class InsertProductService {

	public boolean insertProduct(ProductTBL protbl) {
		Connection con = getConnection();
		
		ManagerDAO mgrDAO = ManagerDAO.getInstance();
		mgrDAO.setConnection(con);
		
		boolean insertProductService = mgrDAO.insertProduct(protbl);
		
		if(insertProductService) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return insertProductService;
	}
	
	
	
}
