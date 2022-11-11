package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductCartQtyDownService;
import vo.ActionForward;

public class ProductCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int product_no = Integer.parseInt(request.getParameter("product_no")) ;
		
		//ProductCartQtyDownService productCartQtyDownService = new ProductCartQtyDownService();
		ProductCartQtyDownService.downCartQty(product_no,request);
		
		forward =new ActionForward("productCartList.shoes",true);
		
		return forward;
	}

}
