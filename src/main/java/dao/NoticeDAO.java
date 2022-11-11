/*
 * CRUD 게시판
 */
package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.NoticeTBL;

public class NoticeDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/*
	 * public NoticeTBLDAO() { try { String dbURL
	 * ="jdbc:mysql://localhost:3306/shoes_shoppingmall"; String dbID ="java";
	 * String dbPassword ="java"; Class.forName("com.mysql.cj.jdbc.Driver"); con =
	 * DriverManager.getConnection(dbURL,dbID,dbPassword); }catch (Exception e) {
	 * e.printStackTrace(); } }
	 */	
	
	//1.생성자는 무조건 private
		private NoticeDAO(){}
		
		private static NoticeDAO noticeDAO;
		//static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 UserDAO객체를 1개만 만들도록 하기 위해
		public static NoticeDAO getInstance(){
			if(noticeDAO == null) {//UserDAO객체가 없으면
				noticeDAO = new NoticeDAO();//객체 생성
			}
			
			return noticeDAO;//기존 UserDAO객체의 주소 리턴
		}
		/************************************************************/
		
		public void setConnection(Connection con){//Connection객체를 받아 DB 연결
			this.con=con;
		}	
	
	//삽입 (C)
	public int insertPost(NoticeTBL notice) {
		int writeCount = 0;
		int notice_no = 0 ;
		
		String sql2 = "select ifnull(max(notice_no),0)+1 as notice_no from noticeTBL";
		//post_no 세팅
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice_no = rs.getInt(1);
			}
			
		} catch (Exception e) {			
			System.out.println("[NoticeDAO] notice_no 불러오기 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		String sql = "insert into noticeTBL"
				+ "(notice_no,notice_subject,notice_text, notice_date) "
				+ " values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);				
			pstmt.setString(2, notice.getNotice_subject());				
			pstmt.setString(3, notice.getNotice_text());			
			pstmt.setString(4, notice.getNotice_date());			
			
			writeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[NoticeDAO] write 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return writeCount;
	}//C
	
	//조회1 (R)
	public ArrayList<NoticeTBL> showList(){
		String sql = "select * from noticeTBL order by notice_no desc ";
		ArrayList<NoticeTBL> list = new ArrayList<NoticeTBL>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeTBL notice = new NoticeTBL(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4) );
				list.add(notice); 
			}
			
		}catch (SQLException e) {
			System.out.println("[NoticeDAO] showList() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//조회2 (R)
		public NoticeTBL showNotice(int notice_no){
			String sql = "select * from noticeTBL where notice_no = ?";
			NoticeTBL notice = null;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, notice_no);;
				rs = pstmt.executeQuery();
				if(rs.next()) {
					notice = new NoticeTBL(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4) );
				}
				
			}catch (SQLException e) {
				System.out.println("[NoticeDAO] showNotice() 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return notice;
		}
		
		//공지글 삭제 메서드
		public boolean deleteNotice(int post_no) {
			String sql = "delete from noticeTBL where post_no ="+post_no+"";
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
	
	//수정 (U)
	
	
	//삭제 (D)

}
