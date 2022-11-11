package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DeleteUserPostService;
import vo.ActionForward;

public class DeleteUserPostAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		DeleteUserPostService deleteUserPostService = new DeleteUserPostService();
		
		boolean deleteSuccess = deleteUserPostService.deletePost(post_no);
		
		if(deleteSuccess) {
			forward = new ActionForward("userBoard.usr",true);
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제가 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
