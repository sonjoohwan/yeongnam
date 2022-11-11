package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.MemberTBL;


public class UserMangementService {
	public ArrayList<MemberTBL> getUserList() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<MemberTBL> userList = userDAO.showUserList();
		
		close(con);		
		
		return userList;
}

}
