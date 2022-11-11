package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.ShowNoticeService;
import vo.ActionForward;
import vo.NoticeTBL;

public class ShowNoticeAcrionMGR implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no")) ;
		
		ShowNoticeService showNoticeService = new ShowNoticeService();
		
		NoticeTBL notice = showNoticeService.getNotice(notice_no);
		
		request.setAttribute("notice", notice);
		request.setAttribute("showPage", "viewNotice.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;

	}

}
