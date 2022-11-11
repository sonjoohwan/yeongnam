<%@page import="org.apache.catalina.User"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>회원관리</title>
</head>

<body>

		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">회원코드</th>
						<th style="background-color: #eeeeee; text-align: center;">회원아이디</th>
						<th style="background-color: #eeeeee; text-align: center;">회원이름</th>
						<th style="background-color: #eeeeee; text-align: center;">회원전화번호</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${userList != null && userList.size() > 0}">
						<!-- 1.order 메뉴목록이 있으면 -->
						<c:forEach var="userL" items="${userList}" varStatus="status">
							<!-- 행 시작  -->
							<tr>
								<td><a href="userManageDetail.mgr?member_code=${userL.member_code}">${userL.member_code }</a></td>
								<td>${userL.member_id}</td>
								<td>${userL.member_name }</td>
								<td>${userL.member_phone }</td>
							</tr>
							<!-- 행 끝 -->
						</c:forEach>
					</c:if>
					<c:if test="${userList == null}">
						<!-- 2.order 메뉴목록이 없으면 -->
						<td colspan="4">등록된 게시글이 없습니다.</td>
					</c:if>
				</tbody>
			</table>

			<a href="userManagement.mgr" class="btn btn-success btn-arraw-left">이전</a>

			<a href="userManagement.mgr" class="btn btn-success btn-arraw-left">다음</a>
			
		</div>


</body>
</html>