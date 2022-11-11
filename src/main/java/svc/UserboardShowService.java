package svc;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.User_boardDAO;
import vo.User_board;
import static db.JdbcUtil.*;

public class UserboardShowService {
	public ArrayList<User_board> getBoardList(HttpServletRequest request, int pageNum){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		ArrayList<User_board> boardList = ubDAO.showList(pageNum);
		int maxPage = ubDAO.maxPage();
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		request.setAttribute("maxPage", maxPage);
		return boardList;
	}
}
