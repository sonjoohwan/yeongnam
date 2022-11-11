package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductCartOrderService;
import vo.ActionForward;
import vo.OrderTBL;

public class OrderResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		HttpSession session = request.getSession();
		
		session.removeAttribute("latestOrder");
		
		String member_id = (String)session.getAttribute("member_id");
		
		if(member_id == null) {//u_id가 null이면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주세요.');");//알림창 띄우고
			out.println("location.href='userLogin.usr';");//다시 로그인 폼보기 요청을 하고
			out.println("</script>");
		}else {//u_id가 null이 아니면
			
			ProductCartOrderService productCartOrderService = new ProductCartOrderService();
			OrderTBL latestOrder = productCartOrderService.userLastOrder(member_id);
			//다시 session영역에 같은 속성명인 latestOrder로 덮어씌워 새로운 값으로 변경됨
			session.setAttribute("latestOrder", latestOrder);
			//successOrder.jsp로 리다이렉트 포워딩
			forward = new ActionForward("successOrder.jsp", true);
		
		}
		return forward;
	}

}
