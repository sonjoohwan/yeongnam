package action.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import action.Action;
import svc.manager.SelectedSalesService;
import vo.ActionForward;
import vo.OrderTBL;

public class SelectedSalesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null ;
		
		//선택된 년과 월 값을 불러와서 하나로 합친 값을 메서드에 넘긴다.
		String year = request.getParameter("selectedYear");
		//year의 값이 0000일때 = 매출관리 페이지에서 년도를 전체로 선택했을때 제일 처음 매출관리페이지로 돌아간다.(매출관리 페이지 다시 여는것과 동일하게)
		if(year.equals("0000")) {
			 return forward = new ActionForward("salesManagement.mgr" , false);
		}
	
		String month = request.getParameter("selectedMonth");
		String selectedYM = String.join("-", year, month); // 테이블에 저장되어있는 값과 동일한 형식으로 '년yyyy-월MM'로 합쳐준다.
		
		System.out.println("selectedYM :"+selectedYM);
		SelectedSalesService selectedSalesService = new SelectedSalesService();
		
		ArrayList<OrderTBL> selectedOrderList = selectedSalesService.selectedOrderList(selectedYM, request);
		//합친 년/월과 request를 넘긴다. request를 넘기는 이유는 session에 들어있는 기존의 totalOrderList를 불러와서 메서드에서 사용하기 위해
		
		request.setAttribute("selectedYear", year);
		request.setAttribute("selectedMonth", month);
		request.setAttribute("totalOrderList", selectedOrderList);
		request.setAttribute("showPage", "totalOrderList.jsp");
		forward = new ActionForward("managerTemplate.jsp" , false);
		
		
		return forward;
	}

}
