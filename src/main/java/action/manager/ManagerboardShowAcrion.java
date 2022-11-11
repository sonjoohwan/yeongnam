package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.UserboardShowService;
import vo.ActionForward;
import vo.User_board;

public class ManagerboardShowAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int pageNum ;
		if(request.getParameter("pageNum")==null) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		UserboardShowService usserboardShowService = new UserboardShowService();
		
		ArrayList<User_board> boardList= usserboardShowService.getBoardList(request, pageNum);

		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("showPage", "../userBoard.jsp");
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;
	}

}
