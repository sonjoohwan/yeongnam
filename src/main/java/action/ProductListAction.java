package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductTBL;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		int productPageNum = 0 ;
		if(request.getParameter("productPageNum")==null) {
			productPageNum = 1;
		}else {
			productPageNum = Integer.parseInt(request.getParameter("productPageNum"));
		}
		
		ProductListService productListService = new ProductListService();
		
		ArrayList<ProductTBL> productList= productListService.getProductList(request,productPageNum);
		
		request.setAttribute("productPageNum", productPageNum);
		request.setAttribute("productList", productList);
		request.setAttribute("showPage", "product/product.jsp");
		forward = new ActionForward("mainTemplate.jsp",false);
		
		return forward;
	}

}
