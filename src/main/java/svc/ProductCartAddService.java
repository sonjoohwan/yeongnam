package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import vo.CartTBL;
import vo.ProductTBL;
import vo.ProductTBL;

public class ProductCartAddService {
	
	/*
	public ProductTBL getCartProduct(int product_no) {
		
		Connection con = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(con);
		
		ProductTBL cartTBL = cartDAO.selectCartTBL(product_no);
		close(con);
		
		return cartTBL;
		
	}
	*/

	//상품정보
	public ProductTBL getProduct(int product_no) {
		
		//커넥션 얻어옴
		Connection con = getConnection();
		//DAO객체 얻어옴
		ProductDAO productDAO = ProductDAO.getInstance();
		//연걸 설정
		productDAO.setConnection(con);
		
		//DAO 메서드로 값 가져옴
		 ProductTBL productInfo = productDAO.selectProductView(product_no);
		close(con);
		
		return productInfo;
	}
	
	@SuppressWarnings("unchecked")
	public void addCartTBL(HttpServletRequest request, ProductTBL productInfo) {
		HttpSession session = request.getSession();
		
		//세션에서 카트목록 가져옴. 세션에서 cartList라는 이름의 속성을 가져와서 ArrayList<CartTBL> cartList 변수에 담는다
		ArrayList<CartTBL> cartList = (ArrayList<CartTBL>)session.getAttribute("cartList");
		
		//카트목록이 null이라면 새로운 목록(비어있는 상태) 생성
		if(cartList == null) {
			cartList = new ArrayList<CartTBL>();
			session.setAttribute("cartList", cartList); //세션에 비어있는 목록을 추가해준다.
		}
		
		//장바구니에 없던 상품인지 체크하는 변수
		boolean isNewCartTBL = true;
		
		//장바구니 탐색
		//장바구니에 있는 항목이면 개수 +1
		// 가져온 상품 정보인 productInfo 를 사용
		for(int i =0; i <cartList.size();i++) { //cartList의 크기만큼 반복하면서
			if(productInfo.getProduct_no() == (cartList.get(i).getProduct_no())) { //cartList 안에 새로 가져온 productInfo와 같은 제품이 있다면
				isNewCartTBL = false; // 새로운 상품이 아니라고 표시하고
				cartList.get(i).setProduct_amount(cartList.get(i).getProduct_amount()+1);// cartList에 담겨져 있는 수량을 변경한다.
				break;
			}
		}
		
		//장바구니에 없는 상품인 경우
		if (isNewCartTBL) {
			//카트에 담음
			CartTBL cartTBL = new CartTBL(); //cartList에 담기 위해 새로운 객체 생성해주고 그 안에 값들을 세팅한다.
			cartTBL.setProduct_no(productInfo.getProduct_no());//여기에 상품번호 추가
			cartTBL.setProduct_image(productInfo.getProduct_image());
			cartTBL.setProduct_name(productInfo.getProduct_name());
			cartTBL.setProduct_price(productInfo.getProduct_price());
			cartTBL.setProduct_amount(1); // 새로 추가되는 상품이라서 수량 1로 시작
			cartList.add(cartTBL); // 값이 세팅된 객체를 cartList에 추가한다.
		}
	}
	
	
}
