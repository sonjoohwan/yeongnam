package action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.UserJoinService;
import vo.ActionForward;
import vo.MemberTBL;

public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int member_code ; 자동입력
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		String member_name = request.getParameter("member_name");
		
		//member_birth String 타입으로 받은것을 Date타입으로 변경해야되는데
		String member_birth = request.getParameter("member_birth"); //일단 String으로 가져와서
		
		String member_phone = request.getParameter("member_phone");
		String member_email = request.getParameter("member_email");
		String member_gender = request.getParameter("member_gender");
		
		//System.out.println("member_birth : "+member_birth);
		//System.out.println("member_birth1 : "+member_birth1);
		//[암호화 방법-3] 생성자에서 비번을 암호화시킴
		MemberTBL member = new MemberTBL( 0 ,member_id, member_pwd, member_name, member_birth, member_phone, member_email, member_gender);
		
		UserJoinService userJoinService = new UserJoinService();
		boolean isUserJoinSuccess = userJoinService.userJoin(member);
		
		if(isUserJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("userLogin.usr", true);//"로그인 폼 보기" 요청(리다이렉트 방식)
		}
		return forward;
	}

}
