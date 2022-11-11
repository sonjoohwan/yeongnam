<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function window_onload(){
		//setInterval을 이용해 일정시간 간격(10초)을 두고 함수를 실행하는 방법
		setInterval('win_refresh()',10000); 
	}
	
	function win_refresh(){
		//[사용자모드]:'구매하기'클릭하여 '실시간주문'요청을 하면
		//[관리자모드]:'실시간주문관리'메뉴에서 order_status를 'order(주문 대기 상태)'->'get(주문승인)' 또는 'order(주문 대기 상태)'->'cancel(주문취소)'로 변경
		location.href="OrderResult.shoes";
	}
</script>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>주문상태확인</title>
</head>
<body onload="javascript:window_onload()">
	
	 <c:if test="${sessionScope.latestOrder ne null}">
		주문번호 : ${sessionScope.latestOrder.order_num}<br>
		주문시간 : ${sessionScope.latestOrder.order_date}<br>
		
		<strong>
		주문상태 :
			<c:choose>
				<c:when test="${sessionScope.latestOrder.order_status eq 'get'}">주문 완료</c:when>
				<c:otherwise>주문 취소</c:otherwise>
			</c:choose>
		</strong>
	</c:if>
	
	
	
	
</body>
</html>