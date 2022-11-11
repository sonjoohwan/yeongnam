package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.manager.SalesManagementService;
import vo.ActionForward;
import vo.OrderTBL;

public class SalesManagementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; 
		
		SalesManagementService salesManagementService = new SalesManagementService();
		
		ArrayList<OrderTBL> totalOrderList = salesManagementService.getTotalOrder();
		
		//request.setAttribute("totalOrderList", totalOrderList);
		HttpSession session = request.getSession();
		session.setAttribute("totalOrderList", totalOrderList);
		request.setAttribute("showPage", "totalOrderList.jsp");
		
		forward = new ActionForward("managerTemplate.jsp" , false);
		
		
		return forward;
	}

}
