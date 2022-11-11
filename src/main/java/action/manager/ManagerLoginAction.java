package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.manager.ManagerLoginService;
import vo.ActionForward;
import vo.ManagerTBL;

public class ManagerLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String manager_id =request.getParameter("manager_id");
		String manager_pwd =request.getParameter("manager_pwd");
		
		ManagerTBL manager = new ManagerTBL();
		manager.setManager_id(manager_id);;
		manager.setManager_pwd(manager_pwd);//암호화X
		//member.setMember_pwd(SHA256.encodeSHA256(member_pwd));//암호와O(사용자가 입력한 암호를 암호화시켜 DB안의 암호화된 비번과 비교)
		
		//로그인 처리를 위한 Service 객체를 생성하여
		ManagerLoginService managerLoginService = new ManagerLoginService();
		//로그인 요청을 처리하는 login()호출(이 때, 매개값:로그인 정보가 저장된 MemberTBL객체)
		boolean isloginSuccess = managerLoginService.login(manager);
		
		ManagerTBL managerInfo = null;		
		if(!isloginSuccess) {//if(isloginResult == false) {//로그인에 실패하면 경고창을 띄운 후 다시 '로그인 폼 보기'요청
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의  : jsp가 아니므로 직접 생성해야 함)
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.');");
			out.println("location.href='managerLogin.adm';");
			out.println("</script>");
			
		}else {//로그인에 성공하면=회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
			//★★입력한 id로 "회원정보"를 가져와(가져오는 이유? 정보를 session영역에 공유하기 위해)
			//ManagerTBL managerInfo = managerLoginService.getManagerInfo(manager_id);
			managerInfo = managerLoginService.getManagerInfo(manager_id);//if문 위에 managerInfo변수 선언
			
			if(managerInfo == null) {//만약 null이면
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 계정입니다.');");
				out.println("location.href='managerLogin.mgr';");//다시 '로그인 폼 보기' 요청
				out.println("</script>");
			}else {//null이 아니면(회원이면)
				/* Cookie와 Session은 둘다 로그인 정보 유지하기 위한 방법
				 * 차이점 : 서버에서 생성한 Cookie는 클라이언트에서 관리(보안에 취약)
				 *       Session은 서버에서 생성하고 관리				 
				 */

				//세션 영역에 속성으로 저장하기 위해 Session객체를 생성					
				HttpSession session = request.getSession();
				//로그인에 성공한 사용자의 u_id,u_password,u_grade,u_name의 이름으로 각 속성값을 공유하여 
				session.setAttribute("manager_id", managerInfo.getManager_id());
				session.setAttribute("manager_pwd", managerInfo.getManager_pwd());
				session.setAttribute("manager_email", managerInfo.getManager_email());
				
				
			
				//web.xml에서 설정가능함
				session.setMaxInactiveInterval(1*60*60);//세션 수명시간을 1시간으로 설정(3600초=1시간)
			
				//★★주의 : 필요한 정보들을 session에 저장했으므로 리다이렉트(true) 방식으로 포워딩해도 무방하다.
				request.setAttribute("showPage", "managerHome.jsp");
				forward = new ActionForward("managerTemplate.jsp", true);//(※false : 리다이렉트 방식으로 포워딩해도 됨)		
			}		
			
		}
		
		
		return forward;
	}

}
