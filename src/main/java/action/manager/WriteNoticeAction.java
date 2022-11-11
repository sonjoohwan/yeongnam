package action.manager;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.WriteNoticeService;
import vo.ActionForward;
import vo.NoticeTBL;

public class WriteNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		//int notice_no ; 자동입력
		String notice_subject = request.getParameter("notice_subject");
		String notice_text = request.getParameter("notice_text");
		
		//작성일 : 오늘날짜
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String notice_date = simpleDateFormat.format(new Date());
		
		NoticeTBL notice = new NoticeTBL();
		notice.setNotice_subject(notice_subject);
		notice.setNotice_text(notice_text);
		notice.setNotice_date(notice_date);
		
		WriteNoticeService writeNoticeService = new WriteNoticeService();
		boolean isWriteSuccess = writeNoticeService.writeAction(notice);
		
		if(isWriteSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("serviceCenter.mgr", true);
		}
		
		return forward;
	}

}
