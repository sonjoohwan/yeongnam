package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartTBL;
import vo.ProductTBL;

public class ProductCartListService {

	public ArrayList<CartTBL> getCartList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//세션에 저장되어 있는 cartList를 불러온다
		ArrayList<CartTBL> cartList = (ArrayList<CartTBL>)session.getAttribute("cartList"); //!: 값이 null 상태
		return cartList;
	}

}
