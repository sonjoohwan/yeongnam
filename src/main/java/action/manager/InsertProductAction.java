package action.manager;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.manager.InsertProductService;
import vo.ActionForward;
import vo.ProductTBL;

public class InsertProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		String category_code = request.getParameter("category_code");
		String product_name = request.getParameter("product_name") ;
		int product_size = Integer.parseInt(request.getParameter("product_size"));
		int product_price = Integer.parseInt(request.getParameter("product_price"));
		int product_amount = Integer.parseInt(request.getParameter("product_amount"));
		String product_decs = request.getParameter("product_decs");
		
		//작성일 : 오늘날짜
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String product_date = simpleDateFormat.format(new Date());
		
		//int product_hits = 0; 0 자동입력
		String product_image = request.getParameter("product_image");
		
		ProductTBL protbl = new ProductTBL(
				product_no, category_code, product_name, product_size, product_price, 
				product_amount, product_decs, product_date, 0, product_image);
		
		
		InsertProductService insertProductService = new InsertProductService();
		boolean insertProductSuccess = insertProductService.insertProduct(protbl);
		
		if(insertProductSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("showProductList.mgr", false);
		}
		
		return forward;
	}

}
