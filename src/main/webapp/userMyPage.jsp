<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<!-- 반응형 웹에 사용하는 메타태그 -->
<title>마이페이지(유저정보수정)</title>
</head>

<body>
	<%
	int member_code = (int) session.getAttribute("member_code");
	String member_id = (String) session.getAttribute("member_id");
	String member_pwd = (String) session.getAttribute("member_pwd");
	String member_name = (String) session.getAttribute("member_name");
	String member_birth = (String) session.getAttribute("member_birth");
	String member_phone = (String) session.getAttribute("member_phone");
	String member_email = (String) session.getAttribute("member_email");
	String member_gender = (String) session.getAttribute("member_gender");
	String address1 = (String) session.getAttribute("address1");
	String address2 = (String) session.getAttribute("address2");
	String address3 = (String) session.getAttribute("address3");
	%>

		<div class="row">
			<form method="post" action="userUpdateAction.usr">
				<input type="hidden" name="member_code" value="<%=member_code %>">
				<h3 style="text-align: center;">등록된 정보</h3>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디" name="member_id" value=<%=member_id == null?"":member_id %> maxlength="12">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="비밀번호" name="member_pwd" value=<%=member_pwd == null?"":member_pwd %> maxlength="12">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="이름" name="member_name" value=<%=member_name == null?"":member_name %> maxlength="12">
				</div>

				<div class="form-group">
					<input type="text" class="form-control" placeholder="생일" name="member_birth" value=<%=member_birth == null?"":member_birth %>>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="번호" name="member_phone" value=<%=member_phone == null?"":member_phone %> maxlength="13">
				</div>
				<div class="form-group">
					<input type="email" class="form-control" placeholder="이메일" name="member_email" value=<%=member_email == null?"":member_email %> maxlength="45">
				</div>
				<div class="form-group" style="text-align: center;">
					<div class="btn-group" data-toggle="button">
						<label class="btn btn-primary active"> 
							<input type="radio" name="member_gender" autocomplete="off" value="M" <%=member_gender.equals("M")?"checked":"" %>>남자
						</label> 
						<label class="btn btn-primary active"> 
							<input type="radio" name="member_gender" autocomplete="off" value="F" <%=member_gender.equals("F")?"checked":"" %>>여자
						</label>
					</div>

				</div>

				<input type="submit" class="btn btn-primary form-control" value="정보수정">
				<input type="submit" class="btn btn-primary form-control" value="계정삭제" formaction="deleteAccount.usr">
			</form>
			<form method="post" action="updateAddressAction.usr">
				<h3 style="text-align : center;"> 주소 입력</h3>
				<input type="hidden" name="member_code" value="<%=member_code %>"><%=member_code %>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="주소1" name="address1" value="<%=address1== null?"":address1 %>" maxlength="20">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="주소2" name="address2" value="<%=address2== null?"":address2 %>" maxlength="20">
				</div>
					<div class="form-group">
					<input type="text" class="form-control" placeholder="주소3" name="address3" value="<%=address3== null?"":address3 %>" maxlength="20">
				</div>
				
				
				<input type="submit" class="btn btn-primary form-control" value="주소등록">
			</form>
		</div>


</body>
</html>