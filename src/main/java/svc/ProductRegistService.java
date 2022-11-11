package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProductDAO;
import vo.ProductTBL;

public class ProductRegistService {
	
	public boolean registProduct(ProductTBL productTBL) {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
	int insertCount = ProductDAO.insertProduct(productTBL);
		
		boolean isRegistSuccess = false;	
		if(insertCount>0){
			commit(con);
			isRegistSuccess=true;
		}else{
			rollback(con);
		}
				close(con); //Connection 객체 해제
				
		return isRegistSuccess;
		
		
	}
	
}
