<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter" %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 참조  -->
<link rel="stylesheet" href="css/footer.css">
<title>메인템플릿</title>
</head>
<body>
	 <%@ include file="head_nav.jsp" %>
    <div class="container" style="height: auto;">
    
	    <c:if test="${showPage ne null }">
		
		<section id="section" style="margin-top: 20px;">	
			<div>
				<jsp:include page="${showPage}" />
			</div>	
		</section>
		
		</c:if>
		
		<c:if test="${showPage eq null }">
	    	 <%@ include file="mainSlide.jsp" %>
	    </c:if>
    </div>
    <%@ include file="footer.jsp" %>
    
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>