package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.net.aso.b;
import vo.ManagerTBL;
import vo.OrderTBL;
import vo.Order_detail;
import vo.ProductTBL;

public class ManagerDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : ManagerDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된
	 * ManagerDAO객체를 공유해서 사용하도록 하기 위해"
	 */

	// 1.생성자는 무조건 private
	private ManagerDAO() {
	}

	private static ManagerDAO mgrDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 ManagerDAO객체를 1개만
	// 만들도록 하기 위해
	public static ManagerDAO getInstance() {
		if (mgrDAO == null) {// ManagerDAO객체가 없으면
			mgrDAO = new ManagerDAO();// 객체 생성
		}

		return mgrDAO;// 기존 ManagerDAO객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	/*
	 * 로그인 폼에서 입력한 id와 pw가 담긴 ManagerTBL객체로 회원인지 조회 후 그 id를 반환 (단, '비밀번호를 암호화'하였다면
	 * 암호화된 비밀번호가 담긴 ManagerTBL객체)
	 */
	public String selectLoginId(ManagerTBL manager) {
		String loginId = null;

		// 암호화X - 사용자가 입력한 id와 비번을 가진 회원의 id를 조회
		String sql = "select manager_id from managerTBL where manager_id=? and manager_pwd=?";

		// 암호화O
		// String sql="select u_id, u_password from user_table where u_id=? and
		// u_password=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, manager.getManager_id());
			pstmt.setString(2, manager.getManager_pwd());
			rs = pstmt.executeQuery();

			// 암호화O
			if (rs.next()) {
				loginId = rs.getString("manager_id");// 방법-2

			}

		} catch (Exception e) {
			System.out.println("[ManagerDAO] selectLoginId 에러:" + e);// e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginId;// 회원가입이 되어있으면 id를 리턴, 그렇지 않으면 null

	}// selectLoginId()

	// user_table안의 회원정보를 u_id로 조회하여 반환
		public ManagerTBL selectManagerInfo(String member_id) {
			ManagerTBL mangerInfo = null;
			// 사용자가 입력한 id 회원정보를 조회
			String sql = "select * from managerTBL where manager_id=?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				rs = pstmt.executeQuery();

				if (rs.next()) {// 해당 id에 대한 정보가 있으면
					// 기본값으로 채워진 ManagerBean객체에 조회한 회원정보값으로 셋팅
					mangerInfo = new ManagerTBL();

					mangerInfo.setManager_id(rs.getString("manager_id"));
					mangerInfo.setManager_pwd(rs.getString("manager_pwd"));
					mangerInfo.setManager_email(rs.getString("manager_email"));
				}
			} catch (Exception e) {
				System.out.println("[ManagerDAO] selectManagerInfo 에러:" + e);// e:예외종류+예외메세지
			} finally {
				close(rs);
				close(pstmt);
			}

			return mangerInfo;
		}

	
	public int insertManager(ManagerTBL manager) {
		int insertManagerCount = 0;
		
		String sql2 = "select manager_id from managerTBL";
		// manager_code 세팅
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if(manager.getManager_id().equals(rs.getString("manager_id"))) {
					
				}
			}

		} catch (Exception e) {
			System.out.println("[ManagerDAO] manager_id 불러오기 에러:" + e);
		} finally {
			System.out.println("manager.getManager_id() : " + manager.getManager_id());
			close(rs);
			close(pstmt);
		}

		// ----------------------------------------------------------------
		String sql = "insert into managerTBL(manager_id, manager_pwd,manager_email) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, manager.getManager_id());
			pstmt.setString(2, manager.getManager_pwd());
			pstmt.setString(3, manager.getManager_email());

			insertManagerCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("[ManagerDAO] insertManager 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertManagerCount;
	}

	
	
	/********** 실시간 주문관리용 메서드 **********************************/
	//마지막 페이지 관리용 메서드
	public int maxPage() {
		int maxPageNum =0 ;
		String sql = "select CEIL(count(*)/10) from orderTBL";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxPageNum = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("[ManagerDAO] maxPage() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return maxPageNum;
	}
	
	public ArrayList<OrderTBL> showList(int pageNum) {
		// 실시간 주문 목록보기
		int pageNum2 =0 ;
		if(1<= pageNum && pageNum <= maxPage()){
			pageNum2 = (pageNum-1)*10;
		}
		
		String sql = "select order_id, member_code ,order_date, order_status from orderTBL order by order_id desc "
				+ "limit "+pageNum2+",10";
		ArrayList<OrderTBL> orderList = new ArrayList<OrderTBL>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderTBL ub = new OrderTBL(
							rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getString(4) );
				orderList.add(ub); 
			}
			
		}catch (SQLException e) {
			System.out.println("[ManagerDAO] showList() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}
	
	//주문 목록 상세보기
	public ArrayList<Order_detail> showDetail(int order_id) {
		// 실시간 주문 목록보기
		String sql = "select order_id, member_id,"
				+ "concat(address1, ' ', address2, ' ', address3) as address, "
				+ "member_phone, member_email, product_no, order_amount, order_price "
				+ "from order_detail natural join orderTBL natural join memberTBL natural join deliver_address  "
				+ "where order_id = ? ";
		ArrayList<Order_detail> detailList = new ArrayList<Order_detail>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Order_detail od = new Order_detail(
							rs.getInt("order_id"),
							rs.getString("member_id"),
							rs.getString("address"),
							rs.getString("member_phone"),
							rs.getString("member_email"),
							rs.getInt("product_no"),
							rs.getInt("order_amount"),
							rs.getInt("order_price") );
				detailList.add(od); 
			}
			
		}catch (SQLException e) {
			System.out.println("[ManagerDAO] showDetail() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return detailList;
		
	}

	/*************************** 관리자용 상품관리**********************************/
	public int maxPagePM() { // 상품관리 페이지 최대 페이지수
		int maxPageNum =0 ;
		String sql = "select CEIL(count(*)/10) from productTBL";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxPageNum = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("[ManagerDAO] maxPagePM() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return maxPageNum;
	}
	public ArrayList<ProductTBL> allProductList(int pageNum) { //상품목록 불러오기
		// 실시간 주문 목록보기
				int pageNum2 =0 ;
				if(1<= pageNum && pageNum <= maxPage()){
					pageNum2 = (pageNum-1)*3;
				}
		String sql = "select product_image, product_no, category_code, product_amount from productTBL order by product_no desc "
				+ "limit "+pageNum2+", 3";
		ArrayList<ProductTBL> allProductList = new ArrayList<ProductTBL>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ProductTBL protbl = null;
			while(rs.next()) {
				 protbl = new ProductTBL(
				rs.getString(1),
				rs.getInt(2),
				rs.getString(3),
				rs.getInt(4)
				);
				allProductList.add(protbl);
				
			}
		}catch (Exception e) {
			System.out.println("[ManagerDAO] allProductList() 오류"+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return allProductList;
	}
	// 상품목록 - 제품상세(수정/삭제 페이지)
	public ProductTBL getProductDetail(int product_no) {
		String sql = "select * from productTBL where product_no =?";
		ProductTBL productTBL = new ProductTBL();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				productTBL = new ProductTBL(
									product_no, //제품번호
									rs.getString(2), //제조사
									rs.getString(3), //제품명
									rs.getInt(4), // 사이즈
									rs.getInt(5), // 가격
									rs.getInt(6), // 재고수량
									rs.getString(7), // 제품설명
									rs.getString(8), // 제품등록일
									rs.getInt(9), // 조회수
									rs.getString(10) // 이미지 파일명
											);
			}else {
				System.out.println("제품상세 불러오기 쿼리 실행 오류");
			}
			
		}catch (Exception e) {
			System.out.println("제품상세 불러오기 메서드 실행 오류"+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return productTBL;
	}
	
	// 상품등록
	public boolean insertProduct(ProductTBL protbl) {
		int insertResult = 0;
		boolean insertSuccess = false;
		
		String sql ="insert into productTBL values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, protbl.getProduct_no());
			pstmt.setString(2, protbl.getCategory_code());
			pstmt.setString(3, protbl.getProduct_name());
			pstmt.setInt(4, protbl.getProduct_size());
			pstmt.setInt(5, protbl.getProduct_price());
			pstmt.setInt(6, protbl.getProduct_amount());
			pstmt.setString(7, protbl.getProduct_decs());
			pstmt.setString(8, protbl.getProduct_date());
			pstmt.setInt(9, protbl.getProduct_hits());
			pstmt.setString(10, protbl.getProduct_image());
			
			insertResult = pstmt.executeUpdate();
			
			if(insertResult > 0) {
				insertSuccess = true;
			}else {
				System.out.println("상품등록 쿼리 오류");
			}
			
		} catch (SQLException e) {
			System.out.println("상품등록 메서드 실행 오류"+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return insertSuccess;
	}
	// 상품 수정
	public boolean updateProduct(ProductTBL protbl) {
		int updateResult = 0;
		boolean updateSuccess = false;
		
		String sql ="update productTBL set product_no=?, category_code=?,product_name=?,product_size=?,product_price=?,product_amount=?,product_decs=?,product_date=?,"
				+ "product_hits=?,product_image=? where product_no="+protbl.getProduct_no();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, protbl.getProduct_no());
			pstmt.setString(2, protbl.getCategory_code());
			pstmt.setString(3, protbl.getProduct_name());
			pstmt.setInt(4, protbl.getProduct_size());
			pstmt.setInt(5, protbl.getProduct_price());
			pstmt.setInt(6, protbl.getProduct_amount());
			pstmt.setString(7, protbl.getProduct_decs());
			pstmt.setString(8, protbl.getProduct_date());
			pstmt.setInt(9, protbl.getProduct_hits());
			pstmt.setString(10, protbl.getProduct_image());
			
			updateResult = pstmt.executeUpdate();
			
			if(updateResult > 0) {
				updateSuccess = true;
			}else {
				System.out.println("상품수정 쿼리 오류");
			}
			
		} catch (SQLException e) {
			System.out.println("상품수정 메서드 실행 오류"+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return updateSuccess;
	}

	public boolean deleteProduct(int product_no) {
		boolean deleteSuccess = false;
		String sql = "delete from productTBL where product_no ="+product_no;
		
		try {
			pstmt = con.prepareStatement(sql);
			int deletedRow = pstmt.executeUpdate();
			if(deletedRow > 0) {
				deleteSuccess = true ;
			}else {
				System.out.println("상품 삭제 쿼리 오류");
			}
		} catch (SQLException e) {
			System.out.println("상품 삭제 메서드 오류"+e);
		}
		
		return deleteSuccess;
	}
		
		
}
 