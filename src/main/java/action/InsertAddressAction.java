package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.InsertAddressService;
import vo.ActionForward;
import vo.Deliver_address;

public class InsertAddressAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int member_code = Integer.parseInt(request.getParameter("member_code"));
		String address1 = request.getParameter("address1"); 
		String address2 = request.getParameter("address2"); 
		String address3 = request.getParameter("address3"); 
		
		Deliver_address da = new Deliver_address();
		da.setMember_code(member_code);
		da.setAddress1(address1);
		da.setAddress2(address2);
		da.setAddress3(address3);
		
		InsertAddressService insertAddress = new InsertAddressService();
		boolean insertAdrSuccess = insertAddress.insertAddress(da);
		
		if(insertAdrSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주소등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			//주소 변경이 완료되었으면 ( 바꾼 주소에 이상이 없으면) 세션에 저장되어있는 주소1,2,3 의 값을 변경해준다(마이페이지에서 바뀐 주소가 바로 적용되게)			
			HttpSession session = request.getSession();
			
			try {
				session.setAttribute("address1", da.getAddress1());
				session.setAttribute("address2", da.getAddress2());
				session.setAttribute("address3", da.getAddress3());
			} catch (Exception e) {
				System.out.println("[InsertAddressAction] session set 에러 : "+e);
			}
		
			//web.xml에서 설정가능함
			session.setMaxInactiveInterval(1*60*60);//세션 수명시간을 1시간으로 설정(3600초=1시간)
		
			forward = new ActionForward("userMyPage.usr", true);//"로그인 폼 보기" 요청(리다이렉트 방식)
		}
		
		return forward;
	}

}
