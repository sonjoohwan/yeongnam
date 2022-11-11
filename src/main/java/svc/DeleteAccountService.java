package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.UserDAO;

public class DeleteAccountService {

	public boolean deleteAccount(int member_code) {
		Connection con = getConnection();
		
		UserDAO uDAO = UserDAO.getInstance();
		uDAO.setConnection(con);
		
		int deleteResult = uDAO.deleteAccount(member_code);
		
		boolean deleteSuccess = false;
		
		if(deleteResult > 0) {
			deleteSuccess = true;
			commit(con);
		}else {
			System.out.println("[DeleteAccountService] : deleteResult < 0");
			rollback(con);
		}
		
		return deleteSuccess;
	}
	
}
