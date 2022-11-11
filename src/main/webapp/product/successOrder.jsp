<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="css/footer.css">
<title>사용자의 주문내역</title>
</head>
<body>
<section>
	<c:if test = "${sessionScope.productList != null && sessionScope.productList.size() > 0}">
		<h2>${sessionScope.member_id }님의 주문내역</h2>
		<form action = "post">
			<table>
				<tr>
					<th>번호</th>
					<th>신발이름</th>
					<th>가격</th>
					<th>수량</th>
				</tr>
				
			<c:forEach var="product" items="${sessionScope.productList}" varStatus = "i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${product.product_name }</td>
					<td>${product.product_price }</td>
					<td>${product.product_amount }</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4"> 합계 금액 : ${sessionScope.totalMoney}원 </td>
			</tr>
			<tr>
				<td colspan="4">
					<jsp:include page="orderResult.jsp" />
				</td>
			</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${sessionScope.productList == null }">
		<h2>${sessionScope.member_id }님의 주문내역이 없습니다.</h2>
	</c:if>
</section>

</body>
</html>