package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.DeleteAccountService;
import vo.ActionForward;

public class DeleteAccountActionMGR implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		int member_code = Integer.parseInt(request.getParameter("member_code"));
		
		DeleteAccountService deleteAccountService = new DeleteAccountService();
		
		boolean deleteAccountSuccess = deleteAccountService.deleteAccount(member_code);
		
		if(deleteAccountSuccess) {
			forward = new ActionForward("userManagement.mgr", true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 탈퇴에 실패했습니다.');");
			out.println("location.href='userManagement.mgr';");//다시 '마이페이지 보기' 요청
			out.println("</script>");
		}
		
		return forward;
	}

}
