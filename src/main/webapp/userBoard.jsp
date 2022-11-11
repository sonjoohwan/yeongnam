<%@page import="org.apache.catalina.User"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="dao.User_boardDAO"%>
<%@page import="vo.User_board"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

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
<%

int pageNum = (int)request.getAttribute("pageNum"); 
int maxPage = (int)request.getAttribute("maxPage");

String member_id = null;
String manager_id = null;

//유저 또는 매니저 로그인 상태에 따라
if (session.getAttribute("member_id") != null) {
	member_id = (String) session.getAttribute("member_id");
}
if (session.getAttribute("manager_id") != null) {
	manager_id = (String) session.getAttribute("manager_id");
}

 %>
		<div class="row" style="margin-bottom: 20px">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${boardList != null && boardList.size() > 0}">
						<!-- 1.board 메뉴목록이 있으면 -->
						<c:forEach var="board" items="${boardList}" varStatus="status">
							<!-- 행 시작  -->
							<tr>
								<td>${board.post_no}</td>
								<td>
								<% if(member_id == null && manager_id !=null){ //관리자 로그인 시 .mgr로 변경 %>
									<a href="showPost.mgr?post_no=${board.post_no}">${board.post_subject }</a>
								<%}else if(manager_id == null){ %>
									<a href="showPost.usr?post_no=${board.post_no}">${board.post_subject }</a>
								<%} %>
								</td>
								<td>${board.member_id }</td>
								<td>${board.post_date }</td>
							</tr>
							<!-- 행 끝 -->
						</c:forEach>
					</c:if>
					<c:if test="${boardList == null}">
						<!-- 2.board 메뉴목록이 없으면 -->
						<td colspan="4">등록된 게시글이 없습니다.</td>
					</c:if>
				</tbody>
			</table>
			<%if(member_id == null && manager_id !=null){%>
				<div class="pageDIV">
				<ul class="pageUL" >
					<%if(pageNum ==1 ) { %>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="firstPage();">이전</a>
					</li>
					<% }else if(pageNum!=1) { %>
					<li class="pageUL_LI">
						<a href="userBoard.mgr?pageNum=${pageNum-1 }" class="btn btn-success">이전</a>
					</li>
					<%} %>
					<c:forEach var="i" begin="1" end="${maxPage }" step="1">
					<li class="pageUL_LI"><a href="userBoard.mgr?pageNum=${i}">${i }</a> </li>
					</c:forEach>
					<%if(pageNum<maxPage){%>
					<li class="pageUL_LI">
						<a href="userBoard.mgr?pageNum=${pageNum+1 }" class="btn btn-success">다음</a>
					</li>
					<%}else if(pageNum == maxPage){%>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="lastPage();">다음</a>
					</li>
					<%} %>
				</ul>
			</div>
			<%}else {%>
			<div class="pageDIV">
				<ul class="pageUL" >
					<%if(pageNum ==1 ) { %>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="firstPage();">이전</a>
					</li>
					<% }else if(pageNum!=1) { %>
					<li class="pageUL_LI">
						<a href="userBoard.usr?pageNum=${pageNum-1 }" class="btn btn-success">이전</a>
					</li>
					<%} %>
					<c:forEach var="i" begin="1" end="${maxPage }" step="1">
					<li class="pageUL_LI"><a href="userBoard.usr?pageNum=${i}">${i }</a> </li>
					</c:forEach>
					<%if(pageNum<maxPage){%>
					<li class="pageUL_LI">
						<a href="userBoard.usr?pageNum=${pageNum+1 }" class="btn btn-success">다음</a>
					</li>
					<%}else if(pageNum == maxPage){%>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="lastPage();">다음</a>
					</li>
					<%} %>
				</ul>
			</div>
			<%} %>
			<%
			if (member_id == null && manager_id ==null) { // 로그인 하지 않았을때 기본적으로 유저 로그인으로 이동시킴
			%>
				<a href="userLogin.usr" class="btn btn-primary pull-right">로그인 후 글쓰기</a> 
			<%
			} else if(member_id != null && manager_id ==null){ // 유저 로그인 시 글쓰기 버튼 표시
			%>
				<a href="userBoardWrite.usr" class="btn btn-primary pull-right">글쓰기</a>
			<%
			}else if(member_id == null && manager_id !=null){ 
				// 관리자 로그인 시 관리자 글쓰기 버튼 표시(유저와 다르게 member_code가 없어서 기능 새로 만들어야함)
			%>
				<a href="managerBoardWrite.mgr" class="btn btn-primary pull-right">관리자 글쓰기</a>
			<%
			}
			%>
		</div>
		
<script>//첫페이지, 마지막페이지 표시용
	function firstPage() {alert("첫 페이지입니다.");}
	function lastPage() {alert("마지막 페이지입니다.");}
</script>

</body>
</html>