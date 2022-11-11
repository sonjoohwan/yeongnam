package svc.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.OrderTBL;

public class SelectedSalesService {

	public ArrayList<OrderTBL> selectedOrderList(String selectedYM, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 1. 세션에서 기존의 totalsOrderList를 불러와서 새로운 ArrayList에 넣어두고 
		ArrayList<OrderTBL> oldOrderList = (ArrayList<OrderTBL>) session.getAttribute("totalOrderList");
		
		// 2. totalOrderList이름의 ArrayList를 새로 만들어서 리턴할 새로운 목록을 만든다 
		ArrayList<OrderTBL> totalOrderList = new ArrayList<OrderTBL>(); // 
		
		for(int i = 0; i<oldOrderList.size() ;i++) {
			OrderTBL ordertbl = oldOrderList.get(i);
			if(selectedYM.equals(ordertbl.getOrder_date().substring(0, 7))) {
				
				totalOrderList.add(ordertbl);
			}
		}
		
		return totalOrderList;
	}

}
