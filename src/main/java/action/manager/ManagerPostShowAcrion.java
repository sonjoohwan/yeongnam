package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.PostShowService;
import vo.ActionForward;
import vo.User_board;

public class ManagerPostShowAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));

		PostShowService postShowService = new PostShowService();
		
		User_board showPost = postShowService.getPost(post_no);
		
		request.setAttribute("showPost", showPost);
		request.setAttribute("showPage", "../view.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;
	}

}
