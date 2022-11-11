package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductCartOrderService;
import vo.ActionForward;
import vo.CartTBL;
import vo.MemberTBL;
import vo.OrderTBL;
import vo.ProductTBL;

public class ProductCartOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		//int totalMoney =  (int)session.getAttribute("totalMoney");
		int totalMoney = Integer.parseInt(request.getParameter("totalMoney"));// 수정사항 : totalMoney가 장바구니 페이지에서 requset값으로 바로 전달되기 때문에 
		//수정사항 : member_code 추가
		int member_code = 0;
		String member_id = (String)session.getAttribute("member_id");
		//수정사항 : member_id는 로그인 확인 시에만 사용하고 메서드에서는 member_code 사용함 (수정된 orderTBL 내 member_id 값 없음)
		if(member_id!=null) {
			member_code = (int)session.getAttribute("member_code");
		}
		
		ArrayList<CartTBL> cartList = (ArrayList<CartTBL>)session.getAttribute("cartList"); // 현재 세션에 저장된 장바구니 목록을 불러와서 cartList에 저장
		
		if(cartList == null) { // 불러온 cartList가 비어있다면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문할 제품을 선택해주세요.')");//알림창 띄우고
			out.println("history.back()");//이전상태로
			out.println("</script>");
		}else {//cartList 속성 안에 값이 존재한다면
			ArrayList<ProductTBL> productList = new ArrayList<ProductTBL>();
			// 선택된 제품리스트를 담을 새로운 ArrayList 생성
			for(int i=0 ; i<cartList.size(); i++) {
				
				//장바구니 목록에서 가져온 정보들로 새로운 객체들을 생성하여
				ProductTBL productTBL = new ProductTBL(
										cartList.get(i).getProduct_no(),
										cartList.get(i).getProduct_name(),
										cartList.get(i).getCategory_code(),
										cartList.get(i).getProduct_price(),
										cartList.get(i).getProduct_amount(),
										cartList.get(i).getProduct_image()
										
						
						);
				productList.add(productTBL);//productList에 추가
			}
		
			// 로그인 확인
			if(member_id == null) { //로그인 되어있지 않으면
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 해주세요.');");//알림창 띄우고
				out.println("location.href='userLogin.usr';");//다시 로그인 폼보기 요청을 하고
				out.println("</script>");
			}else { // 로그인 되어있다면
			
				//System.out.println("totalMoney :" + totalMoney);
				
			ProductCartOrderService productCartOrderService = new ProductCartOrderService();
			
			/* 수정사항 : 받는 값 member_code로 변경함, orderShoes 메서드 매개변수에 totalMoney 추가 */
			//  member_code,productList , totalMoney 를 사용하여 메서드 이용
			boolean isOrderSuccess = productCartOrderService.orderShoes(member_code,productList , totalMoney);
			
			if(!isOrderSuccess) {  // insert 실패시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('주문실패');");//알림창 띄우고
				out.println("history.back();");//이전 상태로 돌아가고
				out.println("</script>");
			}else {
			//insert 성공 
			
			//로그인한 유저의 회원정보
			MemberTBL memberOrder = ProductCartOrderService.MemberOrder(member_id);
			
			//로그인한 유저의 회원정보를 실은 MemberTBL 객체를 설정
			request.setAttribute("memberTBL", memberOrder);
			
			session.setAttribute("productList", productList);
			session.setAttribute("totalMoney", totalMoney);
			
			session.removeAttribute("cartList");
			
			OrderTBL latestOrder = ProductCartOrderService.userLastOrder(member_id);
			
			session.setAttribute("latestOrder", latestOrder);
			
			request.setAttribute("showPage", "./product/order.jsp");
			forward = new ActionForward("mainTemplate.jsp",false);
			}
		  }
		}
		
		return forward;
	}

}
