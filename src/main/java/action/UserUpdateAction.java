package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserJoinService;
import svc.UserUpdateService;
import vo.ActionForward;
import vo.MemberTBL;

public class UserUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//회원코드는 값을 가져오긴 하지만 회원이 변경 불가능한 부분(회원 정보 수정시 이전 작성한 데이터나 남아있는 주소 데이터들과 연결고리로 사용할 값)
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
			//회원정보 수정이 모두 완료되었다면(바꾼 정보에 이상이 없다면) 세션에 저장되어있는 값을 변경
			//세션 영역에 속성으로 저장하기 위해 Session객체를 생성					
			HttpSession session = request.getSession();
			//로그인에 성공한 사용자의 u_id,u_password,u_grade,u_name의 이름으로 각 속성값을 공유하여 
			try {
				session.setAttribute("member_id", member_id);
				session.setAttribute("member_pwd", member_pwd);
				session.setAttribute("member_code", member.getMember_code()); 
				session.setAttribute("member_name", member.getMember_name());
				session.setAttribute("member_birth", member.getMember_birth());
				session.setAttribute("member_phone", member.getMember_phone());
				session.setAttribute("member_email", member.getMember_email());
				session.setAttribute("member_gender", member.getMember_gender());
			} catch (Exception e) {
				System.out.println("[UserUpdateAction] session set1 에러 : "+e);
			}
			
			//web.xml에서 설정가능함
			session.setMaxInactiveInterval(1*60*60);//세션 수명시간을 1시간으로 설정(3600초=1시간)
			forward = new ActionForward("userMyPage.usr", true);// "로그인 폼 보기" 요청(리다이렉트 방식)
		}
		return forward;
	}

}
