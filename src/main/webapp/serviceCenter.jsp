<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>고객센터</title>
<!-- <link rel="stylesheet" type="text/css" href="./script/style.css"> -->
</head>
<body>
	<%
	String manager_id = null;
	if (session.getAttribute("manager_id") != null) {
		manager_id = (String) session.getAttribute("manager_id");
	}
	%>
		<div class="row">
			<!-- 공지사항 -->
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;" colspan="3">공지사항</th>
					</tr>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;" >공지번호</th>
						<th style="background-color: #eeeeee; text-align: center;" >제목</th>
						<th style="background-color: #eeeeee; text-align: center;" >등록일</th>
					</tr>
				</thead>
				<tbody>
					<!-- 게시판 목록 불러오기랑 같은거 써서 불러오기 -->
					<c:if test="${noticeList != null && noticeList.size() > 0}">
						<!-- 1.board 메뉴목록이 있으면 -->
						<c:forEach var="notice" items="${noticeList}" varStatus="status">
							<!-- 행 시작  -->
							<tr>
								<td>${notice.notice_no}</td>
								<td>
									<a href="
									<%if (manager_id == null) {%>viewNotice.usr<%}else{ %>viewNotice.mgr<%}%>
									?notice_no=${notice.notice_no}">${notice.notice_subject }</a>
								</td>
								<td>${notice.notice_date }</td>
							</tr>
							<!-- 행 끝 -->
						</c:forEach>
					</c:if>
					<c:if test="${noticeList == null}">
						<tr>
						<!-- 2.board 메뉴목록이 없으면 -->
							<td colspan="4">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<% if (manager_id != null) { %>
					<tr>
						<td colspan="3" style="background-color: #f3f3f3;">
							<a href="writeNotice.mgr"><input type="button" class="btn form-control" name="submitNotice" value="공지등록"></a>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>

	<h1>1:1문의 서비스 준비 중</h1>
</body>
</html>