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

		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4"
							style="background-color: #eeeeee; text-align: center;">공지사항
							보기</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${notice != null}">
						<tr>
							<td style="width: 25%; background-color: #f0f0f0;">공지 번호</td>
							<td style="width: 25%;">${notice.notice_no}</td>
							<td style="width: 25%; background-color: #f0f0f0;">작성일자</td>
							<td style="width: 25%;">${notice.notice_date }</td>
						</tr>
						<tr>
							<td style="width: 20%; background-color: #f0f0f0;">공지 제목</td>
							<td colspan="3">[&nbsp;&nbsp;${notice.notice_subject}&nbsp;&nbsp;]</td>
						</tr>
						<tr>
							<td colspan="4">내용</td>
						</tr>
						<tr>
							<td colspan="4" style="min-height: 200px; text-align: left; padding-left: 30px; padding-right: 30px;">
								<!-- 특수문자를 제대로 출력하기위해 & 악성스크립트를 방지하기위해 -->
								${notice.notice_text  }
							</td>
						</tr>
						
						
					</c:if>
					<c:if test="${notice == null}">
						<td colspan="4">공지내용을 불러오지 못했습니다.</td>
					</c:if>
				</tbody>
			</table>
			<a href="serviceCenter.mgr" class="btn btn-primary">목록</a>
			
			<%
				String manager_id = null;
				if (session.getAttribute("manager_id") != null) {
					manager_id = (String) session.getAttribute("manager_id");
				}
	
				if (manager_id != null) {
			%>
	            <a href="deleteNotice.mgr?post_no=${notice.notice_no}" class="btn btn-primary pull-right">삭제</a>
				<a href="update.jsp" class="btn btn-primary pull-right">수정</a>
			<%
				}
			%>
		</div>
</body>
</html>
