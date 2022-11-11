package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartTBL;
import vo.ProductTBL;

public class ProductCartRemoveService {

	public static void cartTBLRemove(HttpServletRequest request, String[] product_nameArray) {
		HttpSession session = request.getSession();
		ArrayList<CartTBL> cartList = (ArrayList<CartTBL>)session.getAttribute("cartList");
		
	for (int i = 0; i < product_nameArray.length; i++) {//바깥 for: 삭제할 대상을 
			
			for (int j = 0; j < cartList.size(); j++) {//안쪽 for : 장바구니 목록에서 찾아서 
				
				CartTBL cartTBL= cartList.get(j);
				if(cartTBL.getProduct_name().equals(product_nameArray[i])){//"푸들", equals("푸들")
					cartList.remove(cartTBL);
					break;//가장 가까운 안쪽반복문 빠져나옴
				}
				
			}
			
		}
	}

}
