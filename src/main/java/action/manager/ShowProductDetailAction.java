package action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.ShowProductDetailService;
import vo.ActionForward;
import vo.ProductTBL;

public class ShowProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		
		ShowProductDetailService showProductDetailService = new ShowProductDetailService();
		
		ProductTBL productDetail = showProductDetailService.showProductDetail(product_no);
		
		request.setAttribute("productDetail", productDetail);
		request.setAttribute("showPage", "showProductDetail.jsp");
		
		forward = new ActionForward("managerTemplate.jsp", false);
		
		
		return forward;
	}

}
