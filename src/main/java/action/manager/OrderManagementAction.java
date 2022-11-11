package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.OrderManagementService;
import vo.ActionForward;
import vo.OrderTBL;

public class OrderManagementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int pageNum ;
		if(request.getParameter("pageNum")==null) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		OrderManagementService oderManagementService = new OrderManagementService();
		
		ArrayList<OrderTBL> orderList= oderManagementService.getOrderList(request, pageNum);
		
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("orderList", orderList);
		request.setAttribute("showPage", "orderManagement.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;
		
	}

}
