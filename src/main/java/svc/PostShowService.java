package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.User_boardDAO;
import vo.User_board;

public class PostShowService {
	public User_board getPost(int post_no) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		User_board showPost = ubDAO.showPost(post_no);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return showPost;
		
	}
}
