/**
 *  DB관련 공통 기능 클래스 
 */


package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {//모든 메서드가 static : 객체 생성 없이 바로 메모리에 올라감
	
	//1.커넥션 풀에서 Connection객체를 얻어와 반환
	public static Connection getConnection(){
		Connection con=null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/ipc22bjsk");
			con = ds.getConnection();
			
			con.setAutoCommit(false);//★ Connection객체의 트랙젝션을 자동 commit되지 않도록 하기 위해 꼭넣어야됨
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("JdbcUtil DB연결 오류");
		}
		
		return con;
	}
	
	/*----------------------------------------------------------*/
	//2. Connection 객체를 닫아주는 메서드
	public static void close(Connection con){
		
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//3. Statement 객체를 닫아주는 메서드
	public static void close(Statement stmt){
		
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//4. PreparedStatement 객체를 닫아주는 메서드
	public static void close(PreparedStatement pstmt){
		
		try {
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//5. ResultSet 객체를 닫아주는 메서드
	public static void close(ResultSet rs){
		
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*-----------------------------------------------------------------------*/
	//6. 트랜잭션 중에 실행된 작업들을 '완료' 시키는 메서드 
	public static void commit(Connection con){//commit 메서드
		
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//6. 트랜잭션 중에 실행된 작업들을 '취소' 시키는 메서드 
	public static void rollback(Connection con){
		
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
