package svc.manager;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;
import vo.MemberTBL;

public class UserMngDetailService {

	public MemberTBL getUserDetailInfo(int member_code) {
		Connection con = getConnection(); // import static db.JdbcUtil.*;
		
		UserDAO uDAO = UserDAO.getInstance();
		
		uDAO.setConnection(con);
		
		MemberTBL memberDetail = uDAO.showUserDetail(member_code);
		
		close(con);
		
		return memberDetail;
		
	}

	

}
