package action.manager;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.ShowProductListService;
import vo.ActionForward;
import vo.ProductTBL;

public class ShowProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		int pageNum ;
		if(request.getParameter("pageNum")==null) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		ShowProductListService showProductList = new ShowProductListService();
		
		ArrayList<ProductTBL> productList = showProductList.allProductList(request, pageNum);
		
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("productList", productList);
		request.setAttribute("showPage", "showProductList.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		return forward;
	}

}
