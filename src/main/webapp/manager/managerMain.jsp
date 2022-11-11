<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인 페이지</title>
</head>
<body>

	<div id="myCarousel" class="carousel slide" data-ride="carousel" style="text-align: center; vertical-align: middle;">
    		<%
            	if (manager_id != null){
            %><!-- manager_id 빨간줄 무시 : manager_id 변수선언이 head_nav에 존재하기 때문에 불러오면 자동으로 매칭됨 -->
					<h1 style="line-height: 300px; ">${manager_id}관리자님 환영합니다</h1>
			<%
            	}else {
            		%>
            		<h3 style="line-height: 300px; ">관리자 계정으로 로그인해주세요.</h3>
            		<%
            	}
			%>
    	</div>
</body>
</html>