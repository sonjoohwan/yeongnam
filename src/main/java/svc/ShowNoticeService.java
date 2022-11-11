package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.NoticeTBL;

public class ShowNoticeService {

	public NoticeTBL getNotice(int notice_no) {
		//1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();
				//2.싱글톤 패턴:MenuDAO객체 생성
				NoticeDAO noticeDAO = NoticeDAO.getInstance();
				//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
				noticeDAO.setConnection(con);
				
				/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
				NoticeTBL showNotice = noticeDAO.showNotice(notice_no);
				
				/*-(update,delete,insert)성공하면 commit 실패하면 rollback
				 * (select제외)----*/	
				
				//4.해제
				close(con);//Connection객체 해제		
				
				return showNotice;
	}
	
}
