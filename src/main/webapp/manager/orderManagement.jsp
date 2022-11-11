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
<title>실시간 주문관리</title>

<style type="text/css">

.pageDIV {
	display: flex;
	
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
						<th style="background-color: #eeeeee; text-align: center;">주문번호</th>
						<th style="background-color: #eeeeee; text-align: center;">회원번호</th>
						<th style="background-color: #eeeeee; text-align: center;">주문일</th>
						<th style="background-color: #eeeeee; text-align: center;">주문처리상태</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${orderList != null && orderList.size() > 0}">
						<!-- 1.order 메뉴목록이 있으면 -->
						<c:forEach var="order" items="${orderList}" varStatus="status">
							<!-- 행 시작  -->
							<tr>
								<td><a href="orderDetail.mgr?order_id=${order.order_id}">${order.order_id }</a></td>
								<td>${order.member_code}</td>
								<td>${order.order_date }</td>
								<td>${order.order_status }</td>
							</tr>
							<!-- 행 끝 -->
						</c:forEach>
					</c:if>
					<c:if test="${orderList == null}">
						<!-- 2.order 메뉴목록이 없으면 -->
						<td colspan="4">등록된 게시글이 없습니다.</td>
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
			<a href="orderManagement.mgr?pageNum=${pageNum-1 }" class="btn btn-success">이전</a>
			</li>
			<%} %>
			<c:forEach var="i" begin="1" end="${maxPage }" step="1">
			<li class="pageUL_LI"><a href="orderManagement.mgr?pageNum=${i}">${i }</a> </li>
			</c:forEach>
			<%if(pageNum<maxPage){%>
			<li class="pageUL_LI">
			<a href="orderManagement.mgr?pageNum=${pageNum+1 }" class="btn btn-success">다음</a>
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