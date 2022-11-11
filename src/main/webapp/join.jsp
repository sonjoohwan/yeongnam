<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 참조  -->
<link rel="stylesheet" href="css/custom.css">
<title>로그인 화면</title>
</head>

<body>

		<div class="jumbotron" style="padding-top : 20px;">
			<form method="post" action="userJoinAction.usr">
				<h3 style="text-align : center;"> 회원가입</h3>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디를 입력해주세요.(영문,숫자 혼합 최대 12자리)" name="member_id" id="member_id" maxlength="12">
					<input type="button" name="u_idck" id="u_idck" value="아이디 중복 확인"  class="btn btn-primary form-control"
					onclick="window.open('idCheck/idCheck.jsp?openInit=true','아이디중복확인','top=10, left=10, width=500, height=300')"/>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호(영문, 숫자 혼합 최대 12자리)" name="member_pwd" maxlength="12">
				</div>
					<div class="form-group">
					<input type="text" class="form-control" placeholder="이름" name="member_name" maxlength="12">
				</div>
				
				<div class="form-group">
					<input type="date" class="form-control" placeholder="생일(0000-00-00)" name="member_birth" >
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="번호(-를 포함하여 입력해주세요)" name="member_phone" maxlength="13">
				</div>
				<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="member_email" maxlength="45">
					</div>
					<div class="form-group" style="text-align:center;">
						<div class="btn-group" data-toggle="button">
							<label class="btn btn-primary active">
								<input type="radio" name="member_gender" autocomplete="off" value="M" checked>남자
							</label>
							<label class="btn btn-primary active">
								<input type="radio" name="member_gender" autocomplete="off" value="F" checked>여자
							</label>
						</div>
					
					</div>
				
				<input type="submit" class="btn btn-primary form-control" value="회원가입">
			</form>
		</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
