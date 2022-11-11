package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.NoticeTBL;

public class ServiceCenterService {

	public ArrayList<NoticeTBL> getNoticeList() {
				Connection con = getConnection();
				NoticeDAO noticeDAO = NoticeDAO.getInstance();
				noticeDAO.setConnection(con);
				
				ArrayList<NoticeTBL> noticeList = noticeDAO.showList();
				
				close(con);		
				
				return noticeList;
	}

}
