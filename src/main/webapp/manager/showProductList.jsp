<%@page import="org.apache.catalina.User"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>관리자용 상품목록</title>

<style type="text/css">

.pageDIV {
	display: flex;
	margin-bottom: 15px;
}

.pageUL {
	list-style-type: none;
	margin: 0px auto;
}

.pageUL_LI {
	float: left; 
	margin-right: 20px;
	line-height: 40px;
}

</style>


</head>

<body>
<% int pageNum = (int)request.getAttribute("pageNum"); 
	int maxPage = (int)request.getAttribute("maxPage");
%>
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">제품사진</th>
						<th style="background-color: #eeeeee; text-align: center;">제품번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제조사</th>
						<th style="background-color: #eeeeee; text-align: center;">재고수량</th>
						<th style="background-color: #eeeeee; text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${productList != null && productList.size() > 0}">
						<!-- 1.order 메뉴목록이 있으면 -->
						<c:forEach var="list" items="${productList}" varStatus="status">
							<!-- 행 시작  -->
							
							<tr>
								<td style="vertical-align: middle; width: 40%; height: 100%"><img src="../images/${list.product_image }" style="max-width: 30%; margin: 0px auto;" alt="${list.product_image }" onclick="location.href='showProductDetail.mgr?product_no=${list.product_no}';"></td>
								<td style="vertical-align: middle;">${list.product_no}</td>
								<td style="vertical-align: middle;">${list.category_code }</td>
								<td style="vertical-align: middle;">${list.product_amount }</td>
								<td style="vertical-align: middle;"><input type="button" value="제품상세" onclick="location.href='showProductDetail.mgr?product_no=${list.product_no}';"></td>
							</tr>
							<!-- 행 끝 -->
						</c:forEach>
					</c:if>
					<c:if test="${productList == null}">
						<!-- 2.order 메뉴목록이 없으면 -->
						<td colspan="4">상품 목록을 불러오지 못했습니다.</td>
					</c:if>
				</tbody>
			</table>

		<div class="pageDIV">
			<ul class="pageUL" >
			<%if(pageNum ==1 ) { %>
			<li class="pageUL_LI">
			<a href="#" class="btn btn-success" onclick="firstPage();">이전</a>
			</li>
			<% }else if(pageNum!=1) { %>
			<li class="pageUL_LI">
			<a href="showProductList.mgr?pageNum=${pageNum-1 }" class="btn btn-success">이전</a>
			</li>
			<%} %>
			<c:forEach var="i" begin="1" end="${maxPage }" step="1">
			<li class="pageUL_LI"><a href="showProductList.mgr?pageNum=${i}">${i }</a> </li>
			</c:forEach>
			<%if(pageNum<maxPage){%>
			<li class="pageUL_LI">
			<a href="showProductList.mgr?pageNum=${pageNum+1 }" class="btn btn-success">다음</a>
			</li>
			<%}else if(pageNum == maxPage){%>
			<li class="pageUL_LI">
			<a href="#" class="btn btn-success" onclick="lastPage();">다음</a>
			</li>
			<%} %>
			</ul>
			</div>
		</div>
<script>//첫페이지, 마지막페이지 표시용
	function firstPage() {alert("첫 페이지입니다.");}
	function lastPage() {alert("마지막 페이지입니다.");}
</script>

</body>
</html>