package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.User_boardDAO;


public class DeleteUserPostService {

	public boolean deletePost(int post_no) {
		
		boolean deleteSuccess = false;
		
		Connection con = getConnection();
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		ubDAO.setConnection(con);
		
		deleteSuccess = ubDAO.deletePost(post_no);
		
		if(deleteSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);		
		

		return deleteSuccess;
	}

}
