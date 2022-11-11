<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String member_id = null;
	Cookie loginId ;
	if(session.getAttribute("member_id")!= null){
		member_id = (String) session.getAttribute("member_id");
		loginId = new Cookie("loginId", member_id);
		response.addCookie(loginId);
	}
	
%>
	<nav class ="navbar navbar-default">
        <div class="navbar-header"> <!-- 홈페이지의 로고 -->
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
                <span class ="icon-bar"></span> <!-- 줄였을때 옆에 짝대기 -->
                <span class ="icon-bar"></span>
                <span class ="icon-bar"></span>
            </button>
            <a class ="navbar-brand" href="userHome.usr">ShoesMall</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="userHome.usr">메인</a></li> <!-- 메인 페이지 -->
                <li><a href="productList.shoes">나이키</a></li>
                <li><a href="productList2.shoes">아디다스</a></li>
                <li><a href="userBoard.usr">게시판</a></li>
                <li><a href="serviceCenter.usr">고객센터</a></li>
            </ul>
             <%
            	if (member_id == null){
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" 
                    aria-haspopup="true"
                    aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="userLogin.usr">로그인</a></li>
                        <li><a href="join.usr">회원가입</a></li>
                        <li><a href="productCartList.shoes">장바구니</a></li>                    
                        <li><a href="managerHome.usr">관리자 페이지 이동</a></li>                    
                    </ul>
                </li>
            </ul>
            <%
            	}else{
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" 
                    aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="userLogoutAction.usr">로그아웃</a></li>
                        <li><a href="productCartList.shoes">장바구니</a></li>
                        <li><a href="userMyPage.usr">마이페이지</a></li>
                    </ul>
                </li>
            </ul>
             <% 
            	}
            %>
        </div>
    </nav>
</body>
</html>