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
<title>매출조회</title>
</head>

<body>
<%
int selectedYear =  0;
if(request.getParameter("selectedYear")!= null){
	selectedYear = Integer.parseInt(request.getParameter("selectedYear"))     ;
}

int selectedMonth = 0;
if(request.getParameter("selectedMonth")!= null){
	selectedMonth = Integer.parseInt(request.getParameter("selectedMonth"))      ; 
}

%>
		<div class="row">
			<form action="#" method="post" name="tof">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr> <!-- 년/월 선택하여 월별 매출 조회 -->
						<td colspan="2">
							<select name="selectedYear">
								<option value="0000">전체조회</option>
								<c:forEach var="year" begin="2020" end="2023" step="1" varStatus="loop">
									<option value="${year }" >${year }년</option>
								</c:forEach>
							</select>
						</td>
						<td colspan="2">
							<select name="selectedMonth">
								<c:forEach var="month" begin="1" end="12" step="1" varStatus="loop">
									<option value="${month }" >${month }월</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<input type="submit" name="YMselect" value="선택" formaction="selectedSales.mgr"/>
						</td>
					</tr>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">주문번호-상세주문번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제품번호</th>
						<th style="background-color: #eeeeee; text-align: center;">주문수량</th>
						<th style="background-color: #eeeeee; text-align: center;">주문가격</th>
						<th style="background-color: #eeeeee; text-align: center;">주문일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${totalOrderList != null && totalOrderList.size() > 0}">
						<!-- 1.order 메뉴목록이 있으면 -->
						<c:set var="tp" value="0"/>
						<c:forEach var="totalOrder" items="${totalOrderList}" varStatus="status">
							<!-- 행 시작  -->
							<tr>
								<td>${totalOrder.order_id }&nbsp;-&nbsp;${totalOrder.order_detail_id }</td>
								<td>${totalOrder.product_no}</td>
								<td>${totalOrder.order_amount }</td>
								<td>${totalOrder.order_price }</td>
								<td>${totalOrder.order_date }</td>
							</tr>
							<c:set var="tp" value="${tp + totalOrder.order_price }"/>
							<!-- 행 끝 -->
						</c:forEach>
						<tr>	
							<td colspan="5">
								<%if(selectedYear != 0) {%>
									<%=selectedYear %>년 <%=selectedMonth %> 월 총 매출 : ${tp } 원
								<%} else {%>
									총 매출 : ${tp } 원
								<%}%>
							</td>
						</tr>
					</c:if>
					<c:if test="${totalOrderList == null}">
						<!-- 2.order 메뉴목록이 없으면 -->
						<td colspan="5">매출 정보를 불러오지 못했습니다.</td>
					</c:if>
				</tbody>
			</table>
			</form>
		</div>


</body>
</html>