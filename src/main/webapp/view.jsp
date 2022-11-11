<%@page import="vo.User_board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<!-- 반응형 웹에 사용하는 메타태그 -->
<title>게시판</title>
</head>

<body>
<%
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
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${showPost != null}">
						<tr>
							<td style="width: 20%;">글 제목</td>
							<td colspan="2">
								${showPost.post_subject}
							</td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="2">${showPost.member_id }</td>
						</tr>
						<tr>
							<td>작성일자</td>
							<td colspan="2">${showPost.post_date }</td>
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="2" style="min-height: 200px; text-align: left;">
								<!-- 특수문자를 제대로 출력하기위해 & 악성스크립트를 방지하기위해 -->
								${showPost.post_text }
							</td>
						</tr>
					</c:if>
					<c:if test="${showPost == null}">
						<td colspan="3">post 정보를 불러오지 못했습니다.</td>
					</c:if>
				</tbody>
			</table>
			
			<%
			User_board showPost = (User_board)request.getAttribute("showPost");
			String post_member_id = showPost.getMember_id();
			
			if(member_id == null && manager_id !=null){ //관리자 로그인 시 .mgr로 변경 %>
			
			<a href="userBoard.mgr" class="btn btn-primary">목록</a>
			<a href="deleteUserPost.mgr?post_no=${showPost.post_no}" class="btn btn-primary">삭제</a>
			
			<%}else if(member_id != null && member_id.equals(post_member_id)) { %>
			
			<a href="deleteUserPost.usr?post_no=${showPost.post_no}" class="btn btn-primary">삭제</a>
			<a href="userBoard.usr" class="btn btn-primary">목록</a>
			
			<%}else {%>
			<a href="userBoard.usr" class="btn btn-primary">목록</a>
			<%} %>
			
		</div>
</body>
</html>