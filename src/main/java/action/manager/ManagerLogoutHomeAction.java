package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class ManagerLogoutHomeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		// session.invalidate();//세션의 모든 속성을 삭제-금지(세션에 저장된 사용자 속성도 제거되므로)
		session.removeAttribute("manager_id");
		session.removeAttribute("manager_pwd");

		request.setAttribute("showPage", null);
		forward = new ActionForward("managerTemplate.jsp", true);// 리다이렉트 방식으로 포워딩
		
		return forward;
	}

}
