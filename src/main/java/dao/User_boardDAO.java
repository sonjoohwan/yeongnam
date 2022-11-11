/*
 * CRUD 게시판
 */
package dao;

import static db.JdbcUtil.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import vo.User_board;

public class User_boardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/*
	 * public User_boardDAO() { try { String dbURL
	 * ="jdbc:mysql://localhost:3306/shoes_shoppingmall"; String dbID ="java";
	 * String dbPassword ="java"; Class.forName("com.mysql.cj.jdbc.Driver"); con =
	 * DriverManager.getConnection(dbURL,dbID,dbPassword); }catch (Exception e) {
	 * e.printStackTrace(); } }
	 */	
	
	//1.생성자는 무조건 private
		private User_boardDAO(){}
		
		private static User_boardDAO user_boardDAO;
		//static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 UserDAO객체를 1개만 만들도록 하기 위해
		public static User_boardDAO getInstance(){
			if(user_boardDAO == null) {//UserDAO객체가 없으면
				user_boardDAO = new User_boardDAO();//객체 생성
			}
			
			return user_boardDAO;//기존 UserDAO객체의 주소 리턴
		}
		/************************************************************/
		
		public void setConnection(Connection con){//Connection객체를 받아 DB 연결
			this.con=con;
		}	
	
	//삽입 (C)
	public int insertPost(User_board userboard) {
		int writeCount = 0;
		
		String sql2 = "select ifnull(max(post_no),0)+1 as post_no from user_board";
		//post_no 세팅
		int post_no = 0 ;
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post_no = rs.getInt(1);
			}
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] post_no 불러오기 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		String sql = "insert into user_board"
				+ "(post_no, member_code, post_date, post_pwd,"
				+ "post_subject,post_text, post_file) "
				+ "values(?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, post_no);				
			pstmt.setInt(2, userboard.getMember_code());				
			pstmt.setString(3, userboard.getPost_date());			
			pstmt.setString(4, userboard.getPost_pwd());			
			pstmt.setString(5, userboard.getPost_subject());			
			pstmt.setString(6, userboard.getPost_text());			
			pstmt.setString(7, userboard.getPost_file());			
			
			writeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] write 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return writeCount;
	}//C
	
	//조회 (R)
	//마지막 페이지 관리용 메서드
		public int maxPage() {
			int maxPageNum =0 ;
			String sql = "select CEIL(count(*)/10) from user_board";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					maxPageNum = rs.getInt(1);
				}
				
			}catch (SQLException e) {
				System.out.println("[User_boardDAO] maxPage() 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			return maxPageNum;
		}
	public ArrayList<User_board> showList(int pageNum){
		int pageNum2 =0 ;
		if(1<= pageNum && pageNum <= maxPage()){
			pageNum2 = (pageNum-1)*10;
		}
		
		String sql = "select post_no, post_subject ,member_id, post_date "
				+ "from memberTBL natural join user_board "
				+ "order by post_no desc "
				+ "limit "+pageNum2+",10";
		ArrayList<User_board> list = new ArrayList<User_board>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User_board ub = new User_board(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4) );
				list.add(ub); 
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showList() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//조회 (R)
		public User_board showPost(int post_no){
			String sql = "select post_no, post_subject ,member_id, post_date, post_text from memberTBL natural join user_board where post_no = ?";
			User_board ub = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, post_no);;
				rs = pstmt.executeQuery();
				if(rs.next()) {
					ub = new User_board(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5) );
				}
				
			}catch (SQLException e) {
				System.out.println("[User_boardDAO] showPost() 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return ub;
		}
		
	
	//수정 (U)
	
	
	//삭제 (D)
		
		// 게시물 삭제
		public boolean deletePost(int post_no) {
			String sql = "delete from user_board where post_no ="+post_no+"";
			boolean deleteSuccess = false;
			try {
				pstmt = con.prepareStatement(sql);
				
				int deleteResult = pstmt.executeUpdate();
				
				if(deleteResult>0) {
					deleteSuccess = true;
				}else {
					System.out.println("post 삭제 실패");
				}
				
			}catch (SQLException e) {
				System.out.println("[User_boardDAO] deletePost() 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return deleteSuccess;
		}

}
