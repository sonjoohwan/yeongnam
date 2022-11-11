package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductCartRemoveService;
import vo.ActionForward;

public class ProductCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		String[] product_nameArray = request.getParameterValues("remove");
		
		ProductCartRemoveService productCartRemoveService = new ProductCartRemoveService();
		ProductCartRemoveService.cartTBLRemove(request,product_nameArray);
		
		forward = new ActionForward("productCartList.shoes",true);
		return forward;
	}

}
