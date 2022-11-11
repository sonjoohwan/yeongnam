<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<title>게시판</title>
</head>

<body>
	<% int member_code = (int)session.getAttribute("member_code");%>
	 
	<div class="row">
	<form method="post" action="userBoardWriteAction.usr">
			<input type="hidden" name="member_code" value="${member_code }">
		<table class="table table-striped" style="text-align : center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan ="2" style="background-color : #eeeeee; text-align : center;">게시판 글쓰기 양식</th>
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" placeholder="글제목" name="post_subject" maxlength="50"></td>
				</tr>
				<tr>
					<td><textarea  class="form-control" placeholder="글 내용" name="post_text" maxlength="500" style="height:350px;"></textarea></td>
				</tr>	
				<tr>
					<td><input type="text" class="form-control" placeholder="글 비밀번호" name="post_pwd" maxlength="50"></td>
				</tr>
			</tbody>
		</table>
			<input type="submit"class="btn btn-primary pull-right" value="글쓰기">
			
		</form>
		</div>
	
</body>
</html>