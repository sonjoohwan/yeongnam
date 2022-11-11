package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.UserMangementService;
import vo.ActionForward;
import vo.MemberTBL;

public class UserMangementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserMangementService userManagementService = new UserMangementService();
		
		ArrayList<MemberTBL> userList= userManagementService.getUserList();
		
		request.setAttribute("userList", userList);
		request.setAttribute("showPage", "./userManagement.jsp");
		
		return new ActionForward("managerTemplate.jsp", false);
	}

}
