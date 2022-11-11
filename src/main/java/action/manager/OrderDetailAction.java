package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import svc.manager.OrderDetailService;
import vo.ActionForward;
import vo.Order_detail;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		
		OrderDetailService orderDetailService = new OrderDetailService();
		ArrayList<Order_detail> detailList = orderDetailService.showDetail(order_id);
		
		request.setAttribute("detailList", detailList);
		request.setAttribute("showPage", "orderDetail.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;
	}

}
