package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.NoticeTBL;


public class WriteNoticeService {
	public boolean writeAction(NoticeTBL notice) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();// 바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		// 2.싱글톤 패턴:UserDAO객체 생성
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		noticeDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int writeSuccess = noticeDAO.insertPost(notice);

		boolean isWriteResult = false;
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if (writeSuccess > 0) {// 회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
			isWriteResult = true;
			commit(con);
		} else {
			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제

		return isWriteResult;
	}
}
