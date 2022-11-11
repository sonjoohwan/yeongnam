<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<title>로그인 화면(관리자)</title>

</head>

<body>
	<div class="col-lg-4"></div>
	<div class="col-lg-4"></div>
		<div class="jumbotron" style="padding-top : 20px;">
			<form method="post" action="managerLoginAction.mgr" name="f">
				<h3 style="text-align : center;">관리자 로그인</h3>
				
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디" name="manager_id" id="login_id" maxlength="20">
				</div>
				
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호" name="manager_pwd" maxlength="20">
				</div>
				<input type="submit" class="btn btn-primary form-control" value="로그인">
			</form>
		</div>
	<div class="col-lg-4"></div>


</body>
</html>