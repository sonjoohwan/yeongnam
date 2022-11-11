package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.ServiceCenterService;
import vo.ActionForward;
import vo.NoticeTBL;

public class ServiceCenterAcrionMGR implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServiceCenterService serviceCenterService = new ServiceCenterService();
		
		ArrayList<NoticeTBL> noticeList= serviceCenterService.getNoticeList();
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("showPage", "../serviceCenter.jsp");
		
		return new ActionForward("managerTemplate.jsp", false);
	}

}
