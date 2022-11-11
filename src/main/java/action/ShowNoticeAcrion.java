package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ShowNoticeService;
import vo.ActionForward;
import vo.NoticeTBL;

public class ShowNoticeAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no")) ;
		
		ShowNoticeService showNoticeService = new ShowNoticeService();
		
		NoticeTBL notice = showNoticeService.getNotice(notice_no);
		
		request.setAttribute("notice", notice);
		request.setAttribute("showPage", "./manager/viewNotice.jsp");
		
		forward = new ActionForward("mainTemplate.jsp", false);
		
		return forward;

	}

}
