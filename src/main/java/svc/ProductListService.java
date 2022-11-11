package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.ProductDAO;
import vo.ProductTBL;

public class ProductListService {

	
		public ArrayList<ProductTBL> getProductList(HttpServletRequest request, int productPageNum){
			
			Connection con = getConnection();
			
			ProductDAO productDAO = ProductDAO.getInstance();
			
			productDAO.setConnection(con);
			
			ArrayList<ProductTBL> productList = productDAO.selectProductList(productPageNum);
			int MaxProductPageNum = productDAO.maxPage();
			
			request.setAttribute("MaxProductPageNum", MaxProductPageNum);
			
			close(con);
			
			return productList;
			
			
		}

		public static ProductTBL highPrice(int product_price) {
			Connection con = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(con);
			
			ProductTBL highPrice = productDAO.selectHighPriceList(product_price);
			
			close(con);
			return highPrice;
		}

		public static ProductTBL lowPrice(int product_price) {
			Connection con = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(con);
			
			ProductTBL lowPrice = productDAO.selectLowPriceList(product_price);
			
			close(con);
			return lowPrice;			
		}



	

	
}
