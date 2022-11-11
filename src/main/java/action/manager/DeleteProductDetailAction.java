package action.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.DeleteProductDetailService;
import vo.ActionForward;

public class DeleteProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null ;
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		
		DeleteProductDetailService deleteProductDetailService = new DeleteProductDetailService();
		
		boolean deleteProductSuccess = deleteProductDetailService.deleteProduct(product_no);
		
		if(deleteProductSuccess) {
			
			forward = new ActionForward("showProductList.mgr", false);
			
		}else {
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품 삭제 실패'");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
