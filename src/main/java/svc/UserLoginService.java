package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.UserDAO;
import vo.Deliver_address;
import vo.MemberTBL;

public class UserLoginService {
	//멤버변수
		//생성자
		//메서드
		
		//1.회원 여부
		public boolean login(MemberTBL user){
			//1.커넥션 풀에서 Connection객체 얻어와
			Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
			//2.싱글톤 패턴:UserDAO객체 생성
			UserDAO userDAO = UserDAO.getInstance();
			//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
			userDAO.setConnection(con);
			
			/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
			String loginId = userDAO.selectLoginId(user);
			
			boolean isloginResult = false;
			/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
			 * (select제외)----*/
			if(loginId != null) {//회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
				isloginResult = true;
			}		
			
			//4.해제
			close(con);//Connection객체 해제		
			
			return isloginResult;
		}
		
		public MemberTBL getUserInfo(String member_id) {
			//1.커넥션 풀에서 Connection객체 얻어와
			Connection con = getConnection();
			//2.싱글톤 패턴:UserDAO객체 생성
			UserDAO userDAO = UserDAO.getInstance();
			//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
			userDAO.setConnection(con);
			
			/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
			MemberTBL userInfo = userDAO.selectUserInfo(member_id);
			
			/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
			 * (select제외)----*/				
			
			//4.해제
			close(con);//Connection객체 해제		
			
			return userInfo;
			
		}
		
		public Deliver_address getUserAdrInfo(String member_id) {
			//1.커넥션 풀에서 Connection객체 얻어와
			Connection con = getConnection();
			//2.싱글톤 패턴:UserDAO객체 생성
			UserDAO userDAO = UserDAO.getInstance();
			//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
			userDAO.setConnection(con);
			
			/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
			Deliver_address da = userDAO.selectUserAdrInfo(member_id);
			
			/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
			 * (select제외)----*/				
			
			//4.해제
			close(con);//Connection객체 해제		
			
			return da;
			
		}
		
		

}
