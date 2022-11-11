package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductCartSearchService;
import vo.ActionForward;
import vo.CartTBL;

public class ProductCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		
		ProductCartSearchService productCartSearchService = new ProductCartSearchService(); 
		
		ArrayList<CartTBL> cartList = productCartSearchService.getCartTBLSearchList(startMoney,endMoney,request);//request매개값으로 던지는이유? session넣어두기위해서
		
		request.setAttribute("cartList", cartList);//검색 결과
		request.setAttribute("startMoney", startMoney);
		request.setAttribute("endMoney", endMoney);
		
		int totalMoney = 0;
		
		
		for (int i = 0; i < cartList.size(); i++) {
			//money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			//totalMoney += money;
			totalMoney += cartList.get(i).getProduct_price()*cartList.get(i).getProduct_amount();
		}

		request.setAttribute("totalMoney", totalMoney);
		
		forward = new ActionForward("product/productCartList.jsp", false);
		
		
		return forward;
	}

}
