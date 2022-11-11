package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductCartQtyUpService;
import vo.ActionForward;

public class ProductCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int product_no = Integer.parseInt(request.getParameter("product_no")) ;
		
		//ProductCartQtyUpService productCartQtyUpService = new  ProductCartQtyUpService();
		
		ProductCartQtyUpService.upCartQty(product_no,request);
		
		forward = new ActionForward("productCartList.shoes",true);
		return forward;
	}

}
