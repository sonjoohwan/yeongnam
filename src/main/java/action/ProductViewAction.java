package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductViewService;
import vo.ActionForward;
import vo.ProductTBL;

public class ProductViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ProductViewService productViewService = new ProductViewService();
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		
		ProductTBL productView = productViewService.getProductTBLView(product_no);
		
		
		request.setAttribute("productView", productView);
		request.setAttribute("showPage", "product/productView.jsp");
		forward = new ActionForward("mainTemplate.jsp",false);
		return forward;
	}

}
