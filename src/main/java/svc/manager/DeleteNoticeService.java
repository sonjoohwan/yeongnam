package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import dao.User_boardDAO;

public class DeleteNoticeService {

	public boolean deleteNotice(int post_no) {
		boolean deleteSuccess = false;
		
		Connection con = getConnection();
		NoticeDAO ntcDAO = NoticeDAO.getInstance();
		ntcDAO.setConnection(con);
		
		deleteSuccess = ntcDAO.deleteNotice(post_no);
		
		if(deleteSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);		
		

		return deleteSuccess;
	}

}
