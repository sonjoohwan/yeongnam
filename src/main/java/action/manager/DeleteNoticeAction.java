package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.DeleteNoticeService;
import vo.ActionForward;

public class DeleteNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		DeleteNoticeService deleteNoticeService = new DeleteNoticeService();
		
		boolean deleteSuccess = deleteNoticeService.deleteNotice(post_no);
		
		if(deleteSuccess) {
			forward = new ActionForward("serviceCenter.mgr",true);
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('공지글 삭제가 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
