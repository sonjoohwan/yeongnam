<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자용 헤더 네비</title>
</head>
<body>

<%
	String manager_id = null;
	if(session.getAttribute("manager_id")!= null){
		manager_id = (String) session.getAttribute("manager_id");
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
            <a class ="navbar-brand" href="managerHome.mgr">ShoesMall</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        	<%
            	if (manager_id == null){
            %>
            <ul class="nav navbar-nav">
                <li >관리자용 페이지입니다.</li> <!-- 메인 페이지 -->
                <li >목록을 보려면 로그인해주세요.</li> <!-- 메인 페이지 -->
            </ul>
            <%
            	}else{
            %> 
            <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" 
                    aria-haspopup="true"
                    aria-expanded="false">실시간 주문/매출관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active">
                       		<a href="orderManagement.mgr">실시간 주문관리</a>
                        </li>
                        <li>
                        	<a href="salesManagement.mgr">매출관리</a>
                        </li>                    
                    </ul>
                </li>
                  <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" 
                    aria-haspopup="true"
                    aria-expanded="false">상품관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        
                        <li class="active">
                        	<a href="showProductList.mgr">상품목록</a>
                        </li>
                        
                        <li>
                        	<a href="insertProductPage.mgr">상품등록</a>
                        </li>  
                                          
                    </ul>
                </li>
                <li><a href="userBoard.mgr">게시판관리</a></li>
                <li><a href="userManagement.mgr">회원관리</a></li>
                <li><a href="serviceCenter.mgr">고객센터</a></li>
            </ul>
             <%
            	}
            	if (manager_id == null){
            %>
            <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" 
                    aria-haspopup="true"
                    aria-expanded="false">관리자 접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="managerLogin.mgr">로그인</a></li>                  
                        <li><a href="userHome.mgr">사용자 페이지 이동</a></li>                  
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
                    aria-expanded="false">관리자 메뉴<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="managerLogoutAction.mgr">로그아웃</a></li>
                        <!-- <li><a href="managerJoinAction.mgr">관리자 추가</a></li> -->                  
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