package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.UserUpdateService;
import vo.ActionForward;
import vo.MemberTBL;

public class UserDataUpadate1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		int member_code = Integer.parseInt(request.getParameter("member_code"));
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");

		String member_name = request.getParameter("member_name");

		// member_birth String 타입으로 받은것을 Date타입으로 변경해야되는데
		String member_birth = request.getParameter("member_birth"); // 일단 String으로 가져와서

		String member_phone = request.getParameter("member_phone");
		String member_email = request.getParameter("member_email");
		String member_gender = request.getParameter("member_gender");

		// System.out.println("member_birth : "+member_birth);
		// System.out.println("member_birth1 : "+member_birth1);
		// [암호화 방법-3] 생성자에서 비번을 암호화시킴
		MemberTBL member = new MemberTBL(member_code, member_id, member_pwd, member_name, member_birth, member_phone,
				member_email, member_gender);

		UserUpdateService userUpdateService = new UserUpdateService();
		boolean isUserUpdateSuccess = userUpdateService.userUpdate(member);

		if (isUserUpdateSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보수정 실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward("userManagement.mgr", true); // 회원관리 페이지로 이동
		}
		return forward;
	}

}
