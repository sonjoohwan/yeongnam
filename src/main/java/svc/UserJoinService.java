package svc;

import java.sql.Connection;

import dao.UserDAO;
import vo.MemberTBL;
import static db.JdbcUtil.*;

public class UserJoinService {
	
	public boolean userJoin(MemberTBL member){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		userDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int insertUserCount = userDAO.insertUser(member);
		//int insertUserAdrCount = userDAO.insertUserAdr(member);
		
		boolean isUserJoinResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(insertUserCount>0) {//회원가입에 성공하면
			isUserJoinResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isUserJoinResult;
	}
	
}
