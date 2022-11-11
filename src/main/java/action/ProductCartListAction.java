package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductCartListService;
import vo.ActionForward;
import vo.CartTBL;
import vo.ProductTBL;

public class ProductCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// 서비스
		ProductCartListService productCartListService = new ProductCartListService();
		
		ArrayList<CartTBL> cartList = productCartListService.getCartList(request);
		
		
		int totalMoney =0; // 전체가격 세팅용 변수
		
		//세션에서 불러온 cartList가 비어있지 않을경우
		if(cartList != null) {
		totalMoney = 0; // 먼저 전체 가격을 0으로 초기화한다.
		
		for (int i=0; i<cartList.size();i++) { // cartList에서 제품들의 가격과 수량을 불러와 곱하여 totalMoney에 저장한다.
			totalMoney += cartList.get(i).getProduct_price() * cartList.get(i).getProduct_amount();
		}
		
		HttpSession session = request.getSession();
		
		//세션에 totalMoney를 저장해두고 request에 cartList를 다시 저장하여 보낸다.
		session.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		request.setAttribute("showPage", "product/productCartList.jsp");
		forward = new ActionForward("mainTemplate.jsp",false);
		
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('장바구니가 비어있습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
