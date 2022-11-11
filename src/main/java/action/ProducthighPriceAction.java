package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductTBL;

public class ProducthighPriceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int productPageNum = 0 ;
		if(request.getParameter("productPageNum")==null) {
			productPageNum = 1;
		}else {
			productPageNum = Integer.parseInt(request.getParameter("productPageNum"));
		}
		
		int product_price = Integer.parseInt(request.getParameter("product_price")) ;
		ProductListService productListService = new ProductListService();
		
		ProductTBL highPrice = ProductListService.highPrice(product_price);
		
		ArrayList<ProductTBL> productList= productListService.getProductList(request,productPageNum);
		
		
		request.setAttribute("productPageNum", productPageNum);
		request.setAttribute("product_price", product_price);
		request.setAttribute("productTBL", highPrice);
		request.setAttribute("showPage", "product/product.jsp");
		forward = new ActionForward("mainTemplate.jsp",false);
		return null;
	}

}
