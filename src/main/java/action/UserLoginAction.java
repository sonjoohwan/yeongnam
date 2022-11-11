package action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserLoginService;
import util.SHA256;
import vo.ActionForward;
import vo.Deliver_address;
import vo.MemberTBL;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_id =request.getParameter("member_id");
		String member_pwd =request.getParameter("member_pwd");
		
		MemberTBL member = new MemberTBL();
		member.setMember_id(member_id);;
		member.setMember_pwd(member_pwd);//암호화X
		//member.setMember_pwd(SHA256.encodeSHA256(member_pwd));//암호와O(사용자가 입력한 암호를 암호화시켜 DB안의 암호화된 비번과 비교)
		
		//로그인 처리를 위한 Service 객체를 생성하여
		UserLoginService userLoginService = new UserLoginService();
		//로그인 요청을 처리하는 login()호출(이 때, 매개값:로그인 정보가 저장된 MemberTBL객체)
		boolean isloginSuccess = userLoginService.login(member);
		
		MemberTBL userInfo = null;		
		Deliver_address da = null;		
		if(!isloginSuccess) {//if(isloginResult == false) {//로그인에 실패하면 경고창을 띄운 후 다시 '로그인 폼 보기'요청
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의  : jsp가 아니므로 직접 생성해야 함)
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.');");
			out.println("location.href='userLogin.usr';");
			out.println("</script>");
			
		}else {//로그인에 성공하면=회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
			//★★입력한 id로 "회원정보"를 가져와(가져오는 이유? 정보를 session영역에 공유하기 위해)
			//UserBean userInfo = userLoginService.getUserInfo(u_id);
			userInfo = userLoginService.getUserInfo(member_id);//if문 위(39라인)에 userInfo변수 선언
			da = userLoginService.getUserAdrInfo(member_id);//if문 위(39라인)에 userInfo변수 선언
			
			if(userInfo == null) {//만약 null이면
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 계정입니다.');");
				out.println("location.href='userLogin.usr';");//다시 '로그인 폼 보기' 요청
				out.println("</script>");
			}else {//null이 아니면(회원이면)
				/* Cookie와 Session은 둘다 로그인 정보 유지하기 위한 방법
				 * 차이점 : 서버에서 생성한 Cookie는 클라이언트에서 관리(보안에 취약)
				 *       Session은 서버에서 생성하고 관리				 
				 */

				//세션 영역에 속성으로 저장하기 위해 Session객체를 생성					
				HttpSession session = request.getSession();
				//로그인에 성공한 사용자의 u_id,u_password,u_grade,u_name의 이름으로 각 속성값을 공유하여 
				try {
					session.setAttribute("member_id", member_id);
					session.setAttribute("member_pwd", member_pwd);
					session.setAttribute("member_code", userInfo.getMember_code());
					session.setAttribute("member_name", userInfo.getMember_name());
					session.setAttribute("member_birth", userInfo.getMember_birth());
					session.setAttribute("member_phone", userInfo.getMember_phone());
					session.setAttribute("member_email", userInfo.getMember_email());
					session.setAttribute("member_gender", userInfo.getMember_gender());
				} catch (Exception e) {
					System.out.println("[UserLoginAction] session set1 에러 : "+e);
				}
				
				try {
					session.setAttribute("address1", da.getAddress1());
					session.setAttribute("address2", da.getAddress2());
					session.setAttribute("address3", da.getAddress3());
				} catch (Exception e) {
					System.out.println("[UserLoginAction] session set2 에러 : "+e);
				}
			
				//web.xml에서 설정가능함
				session.setMaxInactiveInterval(1*60*60);//세션 수명시간을 1시간으로 설정(3600초=1시간)
			
				//★★주의 : 필요한 정보들을 session에 저장했으므로 리다이렉트(true) 방식으로 포워딩해도 무방하다.
				request.setAttribute("showPage", null);
				forward = new ActionForward("mainTemplate.jsp", true);		
			}		
			
		}
		return forward;
	}

}
