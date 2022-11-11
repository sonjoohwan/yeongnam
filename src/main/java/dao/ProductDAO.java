package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.OrderTBL;
import vo.ProductTBL;

public class ProductDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt= null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private ProductDAO() {}
	
	private static ProductDAO productDAO;
	
	public static ProductDAO getInstance() {
		
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		
		return productDAO;
	}

	public void setConnection(Connection con) {
			this.con=con;
	}
	//신발 목록 마지막 페이지 구하는 메서드 
	//마지막 페이지 관리용 메서드
		public int maxPage() {
			int maxPageNum =0 ;
			String sql = "select CEIL(count(*)/10) from productTBL";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					maxPageNum = rs.getInt(1);
				}
				
			}catch (SQLException e) {
				System.out.println("[ProductDAO] maxPage() 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			return maxPageNum;
		}
	//모든 신발 목록 조회
	public ArrayList<ProductTBL> selectProductList(int productPageNum) {
		int pageNum2 =0 ;
		if(1<= productPageNum && productPageNum <= maxPage()){
			pageNum2 = (productPageNum-1)*6;
		}
		ArrayList<ProductTBL> productList = null;
		String sql = "select * from productTBL limit "+pageNum2+", 6";
		System.out.println("pageNum2 : "+pageNum2);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ArrayList<ProductTBL>();
				
				do {
					ProductTBL productTBL = new ProductTBL(
							rs.getInt("product_no"),
							rs.getString("category_code"),
							rs.getString("product_name"),
							rs.getInt("product_size"),
							rs.getInt("product_price"),
							rs.getInt("product_amount"),
							rs.getString("product_decs"),
							rs.getString("product_date"),
							rs.getInt("product_hits"),
							rs.getString("product_image")
							);
					productList.add(productTBL);
						
				}while (rs.next());
			}
		}catch (SQLException e) {
			 System.out.println("selectProductTBLList()에러 :" + e );
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}
	
	//product_no에 해당하는 특정 신발 상품 정보 객체로 변환
	public ProductTBL selectProductView(int product_no) {
		ProductTBL productView =null;
		
		String sql = "select * from productTBL where product_no=?";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, product_no);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						productView = new ProductTBL (
								rs.getInt("product_no"),
								rs.getString("category_code"),
								rs.getString("product_name"),
								rs.getInt("product_size"),
								rs.getInt("product_price"),
								rs.getInt("product_amount"),
								rs.getString("product_decs"),
								rs.getString("product_date"),
								rs.getInt("product_hits"),
								rs.getString("product_image")
							
								);
					}
				}catch (SQLException e) {
					System.out.println("selectProductView()에러 : "+e);
				}finally {
					close(rs);
					close(pstmt);
				}
				
		return productView;// 제품 정보를 반환
	}
	
	public int insertProduct(ProductTBL productTBL) {
		int insertCount = 0;
		
		String sql = "insert into productTBL values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productTBL.getProduct_no());
			pstmt.setString(2, productTBL.getCategory_code());
			pstmt.setString(3, productTBL.getProduct_name());
			pstmt.setInt(4, productTBL.getProduct_size());
			pstmt.setInt(5, productTBL.getProduct_price());
			pstmt.setInt(6, productTBL.getProduct_amount());
			pstmt.setString(7, productTBL.getProduct_decs());
			pstmt.setString(8, productTBL.getProduct_date());
			pstmt.setInt(9, productTBL.getProduct_hits());
			pstmt.setString(10, productTBL.getProduct_image());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("insertProduct() :" + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	//조회수 증가 메서드 추가 필요
	public int updateReadCount(int product_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 매출관리 페이지 전체 주문 조회( 매출액, 주문일 포함)
	// 주문 승인 기능 완성 시 orderTBL 테이블에 order_status의 값이 1인 주문(주문승인된 row)만 가져옴
	public ArrayList<OrderTBL> getTotalOrderList() {
		
		String sql = "select order_id, order_detail_id, product_no, order_amount, order_price, order_date "
				+ "from orderTBL natural join order_detail "
				+ "where order_status = 1 order by order_date desc, order_id desc";
		ArrayList<OrderTBL> totalOrderList = new ArrayList<OrderTBL>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderTBL ot = new OrderTBL(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6)
						);
				totalOrderList.add(ot);
			}
		} catch (SQLException e) {
			System.out.println("[ProductDAO] getTotalOrderList() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return totalOrderList;
	}
	
	
	//ver0.0.7 추가되는 것
	// 구매하기 버튼 눌렀을때 나오는 창으로 이동하기 위해 orderTBL과 order_detail 테이블두개에 값을 입력해줘야함
	public int insertOrderProduct(int member_code, ArrayList<ProductTBL> productList, int totalMoney) {
		int insertCount = 0;
		int insertCount2 = 0;
		int newOrder_id = 0; // order_id 자동 세팅용 변수

		// order_id 자동 세팅위해서 sql문 추가 ( 따로 try~catch로 묶어줘야함
		String orderIdSql= "select ifnull(max(order_id),0)+1 from orderTBL"; //orderTBL 안에서 order_id의 최대값을 구하여 +1을 한 값을 구한다
		try {
			pstmt = con.prepareStatement(orderIdSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				newOrder_id = rs.getInt(1);// 쿼리문 실행하여 나온 값을 order_id 에 세팅하기 위해 변수에 담아둔다.
			}else {
				System.out.println("newOrder_id 에러 : "+newOrder_id);
			}
		} catch (SQLException e) {
			System.out.println("orderIdSql 에러");
		}finally {
			System.out.println("newOrder_id : "+newOrder_id);
			close(pstmt);
			close(rs);
		}
		
		
		//orderTBL에 insert하는 부분
		// 새로운 row를 insert하기 위해 sql문 작성
		String sql = "insert into orderTBL(order_id, member_code, order_date,order_status,totalmoney) values(?, ?, now(),'0',?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//?에 들어갈 값
			pstmt.setInt(1, newOrder_id); // order_id에 넣을 값(기존에 있던 값보다 1크게)
			pstmt.setInt(2, member_code); // 
			pstmt.setInt(3, totalMoney); // 
			
			insertCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("orderTBL에 insert하는 부분 :" + e);
		} finally {
			System.out.println("insertOrder : " + insertCount);
			close(pstmt);
		}
		// order_detail에 각 상품별로 insert
		String sql2 = "insert into order_detail(order_detail_id, order_id, product_no, order_amount, order_price) values (?,?,?,?,?)";
		String sql3 = "select max(order_detail_id)+1 from order_detail";
		int newOrderDetailId =0;//order_detail_id를 자동 세팅용 변수
		try {
			for(int i=0; i < productList.size(); i++) {//주문한 내역을 하나씩 가져와
					//주문상세테이블(order_detail)에 insert함					
					pstmt = con.prepareStatement(sql2);
					//반복시 마다 order_detail 테이블에서 최대 order_detail_id 값 +1 을 가져와서 newOrderDetailId에 세팅
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql3);
					if(rs.next()) {
						newOrderDetailId = rs.getInt(1);
					}
					
					ProductTBL product = productList.get(i);
					pstmt.setInt(1, newOrderDetailId);
					pstmt.setInt(2, newOrder_id);//★★주의  : 1에서 insert한 가장 최근 주문번호
					pstmt.setInt(3, product.getProduct_no()); //주문한 제품id
					pstmt.setInt(4, product.getProduct_amount()); //주문한 제품 수량
					pstmt.setInt(5, product.getProduct_price()); //주문한 제품 가격
					
					int executedNum = pstmt.executeUpdate();
					if(executedNum > 0) {
						insertCount2++;
					}
				}
			
			
		}catch(Exception e){
			System.out.println("order_detail에 각 상품별로 insert :" + e);
		} finally {
			System.out.println("insertOrder2 : " + insertCount2);
			close(pstmt);
			close(stmt);
			close(rs);
		}
		if(insertCount>0 && insertCount2 >0) {
		return insertCount;
		}else {
			return 0;
		}
	}

	public ProductTBL selectHighPriceList(int product_price) {
		int pageNum2 =0 ;
		int productPageNum = 0;
		if(1<= productPageNum && productPageNum <= maxPage()){
			pageNum2 = (productPageNum-1)*6;
		}
	ProductTBL productList = null;
		String sql = "select * from productTBL limit order by product_price asc"+pageNum2+", 6" ;
		System.out.println("pageNum2 : "+pageNum2);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ProductTBL();
				
				do {
					ProductTBL productTBL = new ProductTBL(
							rs.getInt("product_no"),
							rs.getString("category_code"),
							rs.getString("product_name"),
							rs.getInt("product_size"),
							rs.getInt("product_price"),
							rs.getInt("product_amount"),
							rs.getString("product_decs"),
							rs.getString("product_date"),
							rs.getInt("product_hits"),
							rs.getString("product_image")
							);
						
				}while (rs.next());
			}
		}catch (SQLException e) {
			 System.out.println("selectProductTBLList()에러 :" + e );
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}

	public ProductTBL selectLowPriceList(int product_price) {
		int pageNum2 =0 ;
		int productPageNum = 0;
		if(1<= productPageNum && productPageNum <= maxPage()){
			pageNum2 = (productPageNum-1)*6;
		}
		ProductTBL productList = null;
		String sql = "select * from productTBL limit product_price desc "+pageNum2+", 6" ;
		System.out.println("pageNum2 : "+pageNum2);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ProductTBL();
				
				do {
					ProductTBL productTBL = new ProductTBL(
							rs.getInt("product_no"),
							rs.getString("category_code"),
							rs.getString("product_name"),
							rs.getInt("product_size"),
							rs.getInt("product_price"),
							rs.getInt("product_amount"),
							rs.getString("product_decs"),
							rs.getString("product_date"),
							rs.getInt("product_hits"),
							rs.getString("product_image")
							);
						
				}while (rs.next());
			}
		}catch (SQLException e) {
			 System.out.println("selectProductTBLList()에러 :" + e );
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}






}
