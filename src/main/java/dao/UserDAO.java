package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.Cookie;

import com.mysql.cj.Session;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import util.SHA256;
import vo.Deliver_address;
import vo.MemberTBL;

public class UserDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : UserDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된
	 * UserDAO객체를 공유해서 사용하도록 하기 위해"
	 */

	// 1.생성자는 무조건 private
	private UserDAO() {
	}

	private static UserDAO userDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 UserDAO객체를 1개만 만들도록
	// 하기 위해
	public static UserDAO getInstance() {
		if (userDAO == null) {// UserDAO객체가 없으면
			userDAO = new UserDAO();// 객체 생성
		}

		return userDAO;// 기존 UserDAO객체의 주소 리턴
	}

/************************************************************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}
/************************************************************************************************************/
	/*
	 * 로그인 폼에서 입력한 id와 pw가 담긴 UserBean객체로 회원인지 조회 후 그 id를 반환 (단, '비밀번호를 암호화'하였다면
	 * 암호화된 비밀번호가 담긴 UserBean객체)
	 */
	public String selectLoginId(MemberTBL member) {
		String loginId = null;

		// 암호화X - 사용자가 입력한 id와 비번을 가진 회원의 id를 조회
		String sql = "select member_id from memberTBL where member_id=? and member_pwd=?";

		// 암호화O
		// String sql="select u_id, u_password from user_table where u_id=? and
		// u_password=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pwd());
			rs = pstmt.executeQuery();

			// 암호화O
			if (rs.next()) {
				loginId = rs.getString("member_id");// 방법-2

			}

		} catch (Exception e) {
			System.out.println("[UserDAO] selectLoginId 에러:" + e);// e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginId;// 회원가입이 되어있으면 id를 리턴, 그렇지 않으면 null

	}// selectLoginId()
/************************************************************************************************************/
	// user_table안의 회원정보를 u_id로 조회하여 반환
	public MemberTBL selectUserInfo(String member_id) {
		MemberTBL userInfo = null;
		// 사용자가 입력한 id 회원정보를 조회
		String sql = "select * from memberTBL where member_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				// 기본값으로 채워진 UserBean객체에 조회한 회원정보값으로 셋팅
				userInfo = new MemberTBL();
				userInfo.setMember_code(rs.getInt("member_code"));
				userInfo.setMember_id((rs.getString("member_id")));
				userInfo.setMember_name((rs.getString("member_name")));
				userInfo.setMember_birth((rs.getString("member_birth")));
				userInfo.setMember_phone((rs.getString("member_phone")));
				userInfo.setMember_email((rs.getString("member_email")));
				userInfo.setMember_gender((rs.getString("member_gender")));
			}
		} catch (Exception e) {
			System.out.println("[UserDAO] selectUserInfo 에러:" + e);// e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}

		return userInfo;
	}
/************************************************************************************************************/
	public Deliver_address selectUserAdrInfo(String member_id) {
		Deliver_address da = new Deliver_address();
		// 사용자가 입력한 id 회원정보를 조회
		String sql = "select * from memberTBL natural join deliver_address where member_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				// 기본값으로 채워진 UserBean객체에 조회한 회원정보값으로 셋팅
				da.setAddress1(rs.getString("address1"));
				da.setAddress2(rs.getString("address2"));
				da.setAddress3(rs.getString("address3"));
			}
		} catch (Exception e) {
			System.out.println("[UserDAO] selectUserAdrInfo 에러:" + e);// e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}

		return da;
	}
/************************************************************************************************************/
	public int insertUser(MemberTBL member) {
		int insertUserCount = 0;
		int insertUserAdrCount = 0;

		String sql2 = "select ifnull(max(member_code),0)+1 as member_code from memberTBL";
		// member_code 세팅
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setMember_code(rs.getInt(1));
			}

		} catch (Exception e) {
			System.out.println("[UserDAO] member_code 불러오기 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		// ----------회원 가입시 멤버테이블에 회원 정보를 insert하면서 주소 테이블에 멤버 코드를 제외한 값을 null로 하여 미리 값을 생성한다.
		//이후 주소 등록 페이지에서는 update를 사용하여 주소 등록시 같은 member_code로 새로운 주소가 등록되는 것이 아니고 기존의 row에 column값만 변경한다.
		String sql = "insert into memberTBL(member_code, member_id,member_pwd,member_name,member_birth,member_phone,member_email,member_gender) values(?,?,?,?,?,?,?,?)";
		//아래는 주소테이블에 null값으로 미리 만들어 두는 쿼리문
		String sql3 = "insert into deliver_address values(?, '','','')";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, member.getMember_code());
			pstmt.setString(2, member.getMember_id());
			pstmt.setString(3, member.getMember_pwd());
			pstmt.setString(4, member.getMember_name());
			pstmt.setString(5, member.getMember_birth());
			pstmt.setString(6, member.getMember_phone());
			pstmt.setString(7, member.getMember_email());
			pstmt.setString(8, member.getMember_gender());
			//pstmt.setInt(9, member.getMember_code());

			insertUserCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음
			
			if(insertUserCount>0) { // 먼저 회원가입을 성공한 뒤에 주소 등록실시한다.
			pstmt = con.prepareStatement(sql3);
			
			pstmt.setInt(1, member.getMember_code());

			insertUserAdrCount = pstmt.executeUpdate();
			}else {
				System.out.println("회원가입 실패");
			}
			
		} catch (Exception e) {
			System.out.println("[UserDAO] insertUser 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertUserCount;
	}
	
/****************************************************************************************************************/
	public int insertAdr(Deliver_address da) {
		//회원 가입시 멤버코드를 제외한 컬럼은 null로 채워져있고 본 메서드 실행시 업데이트 구문을 통해 주소값 입력함
		//만들때 insert로 이름 지었지만 현재 update 역할 수행중 추후에 필요시 이름 변경할것
		int insertAdrCount = 0;

		String sql = "update deliver_address set address1=?, address2=?, address3=?  where member_code=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(4, da.getMember_code());
			pstmt.setString(1, da.getAddress1());
			pstmt.setString(2, da.getAddress2());
			pstmt.setString(3, da.getAddress3());

			insertAdrCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("[UserDAO] insertAdr 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertAdrCount;
	}
/****************************************************************************************************************/
	public int updateUser(MemberTBL member) {
		// 회원 정보 수정 메서드
		int updateUserCount = 0;


		String sql = "update memberTBL set member_id=?,member_pwd=?,member_name=?,member_birth=?,member_phone=?,member_email=?,member_gender=?"
				+ " where member_code=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(8, member.getMember_code());
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pwd());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_birth());
			pstmt.setString(5, member.getMember_phone());
			pstmt.setString(6, member.getMember_email());
			pstmt.setString(7, member.getMember_gender());

			updateUserCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("[UserDAO] updatetUser 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateUserCount;
	}
/************************************************************************************************************/
	//관리자의 회원관리 페이지 회원정보 불러오기 메서드
public ArrayList<MemberTBL> showUserList() {
	String sql = "select member_code, member_id, member_name, member_phone from memberTBL order by 1 desc";
	ArrayList<MemberTBL> showUserList = new ArrayList<MemberTBL>() ;
	
	try {
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MemberTBL m_tbl = new MemberTBL();
			m_tbl.setMember_code(rs.getInt(1));
			m_tbl.setMember_id(rs.getString(2));
			m_tbl.setMember_name(rs.getString(3));
			m_tbl.setMember_phone(rs.getString(4));
			
			showUserList.add(m_tbl);
		}

	} catch (Exception e) {
		System.out.println("[UserDAO] showUserList 에러:" + e);
	} finally {
		close(rs);
		close(pstmt);
	}
	return showUserList;
}

public MemberTBL showUserDetail(int member_code) {
	String sql = "select member_code, member_id, member_pwd, member_name, member_birth, "
			+ "member_phone, member_email, member_gender, "
			+ "address1, address2, address3 "
			+ "from memberTBL natural join deliver_address where member_code = ? ";
	MemberTBL memberInfo = null;
	
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, member_code);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			memberInfo = new MemberTBL(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11)
					);
		}
	}catch (Exception e) {
		System.out.println("[UserDAO] showUserDetail 오류"+e);
	}
	return memberInfo;
}

public int deleteAccount(int member_code) {
	int deleteResult = 0 ;
	
	String sql = "delete from memberTBL where member_code ="+member_code;
	
	try {
		pstmt = con.prepareStatement(sql);
		deleteResult = pstmt.executeUpdate();
		
	} catch (Exception e) {
		System.out.println(e);
	}
	return deleteResult;
}

//주문자정보?
	public MemberTBL selectMemberOrder(String member_code) {
		MemberTBL memberOrder = null;
		
		String sql="SELECT member_id,member_name,member_phone,member_email,address1,address2,address3 "
					+ " from deliver_address natural join memberTBL where member_id=?";		

		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, member_code);		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//해당 id에 대한 정보가 있으면
				
				memberOrder = new MemberTBL();
				
				memberOrder.setMember_id((rs.getString("member_id")));
				memberOrder.setMember_name((rs.getString("member_name")));
				memberOrder.setMember_phone((rs.getString("member_phone")));
				memberOrder.setAddress1((rs.getString("address1")));
				memberOrder.setAddress2((rs.getString("address2")));
				memberOrder.setAddress3((rs.getString("address3")));
				memberOrder.setMember_email((rs.getString("member_email")));
			}
		} catch (Exception e) {			
			System.out.println("[MemberDAO] selectMemberInfo 에러:"+ e);//e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}			
		
		return memberOrder;
	}


/************************************************************************************************************/
	
	
}// class
