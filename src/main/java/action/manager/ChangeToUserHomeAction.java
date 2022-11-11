package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class ChangeToUserHomeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		HttpSession session = request.getSession();
		session.removeAttribute("manager_id");
		session.removeAttribute("manager_pwd");

		request.setAttribute("showPage", null);
		forward = new ActionForward("../mainTemplate.jsp", true); // 반드시 디스패치 방식으로 포워딩
		
		return forward;
	}

}
