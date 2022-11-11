<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<!-- 반응형 웹에 사용하는 메타태그 -->
<title>회원관리(유저정보수정)</title>
</head>

<body>
		<div class="row">
		
			<c:if test="${memberDetail != null}">
			
				<form method="post" action="userDataUpdate1.mgr" style="background-color: #f3f3f3;">
					<div class="form-group" style="text-align: center;">
						<input type="hidden" class="form-control" name="member_code" value="${memberDetail. member_code}" readonly="readonly">
						<h3>${memberDetail. member_code}번 회원님의</h3>
					</div>
					
					<h3 style="text-align: center;">등록된 정보</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="member_id" value="${memberDetail.member_id }" maxlength="12">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="비밀번호" name="member_pwd" value="${memberDetail.member_pwd }" maxlength="12">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="member_name" value="${memberDetail.member_name }" maxlength="12">
					</div>
	
					<div class="form-group">
						<input type="text" class="form-control" placeholder="생일" name="member_birth" value="${memberDetail.member_birth }">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="번호" name="member_phone" value="${memberDetail.member_phone }" maxlength="13">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="member_email" value="${memberDetail.member_email }" maxlength="45">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="button">
							<label class="btn btn-primary active"> 
								<input type="radio" name="member_gender" autocomplete="off" value="M" ${memberDetail.member_gender.equals("M")?"checked":""  }>남자
							</label> 
							<label class="btn btn-primary active"> 
								<input type="radio" name="member_gender" autocomplete="off" value="F" ${memberDetail.member_gender.equals("F")?"checked":""  }>여자
							</label>
						</div>
	
					</div>
	
					<input type="submit" class="btn btn-primary form-control" value="정보수정">
					<input type="submit" class="btn btn-primary form-control" value="회원탈퇴" formaction="deleteUserAccount.mgr?member_code=${memberDetail. member_code}"/>
				</form>
				
				<!-- 회원 정보 / 주소 --------------------------------------------------------------------------------------- -->
				<form method="post" action="userDataUpdate2.mgr" style="background-color: #f3f3f3;">
					<h3 style="text-align : center;"> 주소 수정</h3>
					<input type="hidden" name="member_code" value="${memberDetail.member_code }">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소1" name="address1" value="${memberDetail.address1 }" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소2" name="address2" value="${memberDetail.address2 }" maxlength="20">
					</div>
						<div class="form-group">
						<input type="text" class="form-control" placeholder="주소3" name="address3" value="${memberDetail.address3 }" maxlength="20">
					</div>
					
					
					<input type="submit" class="btn btn-primary form-control" value="주소등록">
				</form>
			
			</c:if>
			
			<c:if test="${memberDetail == null }">회원정보를 불러오지 못했습니다.</c:if>
			
			
		</div>


</body>
</html>