package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.UserMngDetailService;
import vo.ActionForward;
import vo.MemberTBL;

public class UserManagementDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int member_code = Integer.parseInt(request.getParameter("member_code")) ;
		
		UserMngDetailService userMngDetailService = new UserMngDetailService();
		
		MemberTBL memberDetail = userMngDetailService.getUserDetailInfo(member_code);
		
		request.setAttribute("memberDetail", memberDetail);
		request.setAttribute("showPage", "userManagementDetail.jsp");
		
		return new ActionForward("managerTemplate.jsp", false);
	}

}
