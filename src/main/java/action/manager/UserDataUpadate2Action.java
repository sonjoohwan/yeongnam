package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.InsertAddressService;
import vo.ActionForward;
import vo.Deliver_address;

public class UserDataUpadate2Action implements Action {

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
			out.println("alert('주소수정 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("userManagement.mgr", true);
		}
		
		return forward;
	}

}
