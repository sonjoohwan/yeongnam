package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ManagerDAO;
import vo.ManagerTBL;


public class ManagerLoginService {
	//1.회원 여부
			public boolean login(ManagerTBL manager){
				//1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
				//2.싱글톤 패턴:ManagerDAO객체 생성
				ManagerDAO managerDAO = ManagerDAO.getInstance();
				//3.DB작업에 사용될 Connection객체를 ManagerDAO의 멤버변수로 삽입하여 DB 연결
				managerDAO.setConnection(con);
				
				/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
				String loginId = managerDAO.selectLoginId(manager);
				
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
			
			public ManagerTBL getManagerInfo(String manager_id) {
				//1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();
				//2.싱글톤 패턴:ManagerDAO객체 생성
				ManagerDAO managerDAO = ManagerDAO.getInstance();
				//3.DB작업에 사용될 Connection객체를 ManagerDAO의 멤버변수로 삽입하여 DB 연결
				managerDAO.setConnection(con);
				
				/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
				ManagerTBL managerInfo = managerDAO.selectManagerInfo(manager_id);
				
				/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
				 * (select제외)----*/				
				
				//4.해제
				close(con);//Connection객체 해제		
				
				return managerInfo;
				
			}
}
