package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartTBL;

public class ProductCartSearchService {

	public ArrayList<CartTBL> getCartTBLSearchList(int startMoney, int endMoney, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		ArrayList<CartTBL> oldCartList = (ArrayList<CartTBL>)session.getAttribute("cartList");
		
		//cartList : 검색 후 (기본값으로 채워짐 -> 
		ArrayList<CartTBL> cartList = new ArrayList<CartTBL>();//가격범위검색해서 CARTLIST에 넣어준다
		for (int i = 0; i < oldCartList.size(); i++) {
			
			CartTBL cartTBL = oldCartList.get(i);
			
			if(startMoney <= cartTBL.getProduct_price()  && cartTBL.getProduct_price() <= endMoney){
				cartList.add(cartTBL);
			}
			
		}
		
		
		return cartList;
	}

}
