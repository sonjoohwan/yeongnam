package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductCartAddService;
import vo.ActionForward;
import vo.CartTBL;
import vo.ProductTBL;

public class ProductCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		ProductCartAddService productCartAddService = new ProductCartAddService();
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));

		//넘어온 파라미터로 제품 정보를 얻어옴
		ProductTBL productInfo = productCartAddService.getProduct(product_no);
		//CartTBL cartProduct = productCartAddService.getCartProduct(product_no);
		
		//얻어온 제품 정보를 CartTBL에 추가 ( 세션영역의 cartList=장바구니 정보에 제품정보를 추가한다.)
		productCartAddService.addCartTBL(request, productInfo);
		
		
		forward = new ActionForward("productCartList.shoes", true);//장바구니 페이지로 이동하는 경로로 이동한다.
		
		return forward;
	}

}
