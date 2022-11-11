<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 참조  -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="css/login.css">
<title>로그인 화면</title>
<script type="text/javascript">
	function save_idCheckCheck() {
		if(f.remember.checked){
			f.remember.value = 'checked';
		}else{
			f.remember.value = null;
		}
			
	}
</script>
</head>

<body>
    <%
	String member_id = null;
	if(session.getAttribute("member_id")!= null){
		member_id = (String) session.getAttribute("member_id");
	}
	
%>
<%--  	<div class="col-lg-4"></div>
		<div class="jumbotron" style="padding-top : 20px;">
			<form method="post" action="userLoginAction.usr" name="f">
				<h3 style="text-align : center;"> 로그인</h3>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디" name="member_id" id="login_id" maxlength="20" value="${cookie.login_id.value }">
				</div>
					<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호" name="member_pwd" maxlength="20">
				</div>
				<input type="checkbox" name="remember" class="remember" onclick="save_idCheckCheck()" ${(cookie.save_id.value != null)?'checked':null}/>아이디 저장
				<input type="submit" class="btn btn-primary form-control" value="로그인">
			</form>
		</div>
	<div class="col-lg-4"></div>  --%>



    

    <div class="omb_login">
    	<h1 class="omb_authTitle">Login</h1>
		<div class="row omb_row-sm-offset-3 omb_socialButtons">
    	    <div class="col-xs-4 col-sm-2">
		        <a href="https://ko-kr.facebook.com/facebook/" class="btn btn-lg btn-block omb_btn-facebook">
			        <i class="fa fa-facebook visible-xs"></i>
			        <span class="hidden-xs">Facebook</span>
		        </a>
	        </div>
        	<div class="col-xs-4 col-sm-2">
		        <a href="https://twitter.com/" class="btn btn-lg btn-block omb_btn-twitter">
			        <i class="fa fa-twitter visible-xs"></i>
			        <span class="hidden-xs">Twitter</span>
		        </a>
	        </div>	
        	<div class="col-xs-4 col-sm-2">
		        <a href="https://www.google.co.kr/" class="btn btn-lg btn-block omb_btn-google">
			        <i class="fa fa-google-plus visible-xs"></i>
			        <span class="hidden-xs">Google+</span>
		        </a>
	        </div>	
		</div>

		<div class="row omb_row-sm-offset-3 omb_loginOr">
			<div class="col-xs-12 col-sm-6">
				<hr class="omb_hrOr">
				<span class="omb_spanOr">or</span>
			</div>
		</div>

		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-6">	
			   <form method="post" action="userLoginAction.usr" name="f">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text" class="form-control" name="member_id" id="login_id" maxlength="20" value="${cookie.login_id.value }" placeholder="아이디 입력">
					</div>
					<span class="help-block"></span>
										
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input  type="password" class="form-control" name="member_pwd" placeholder="비밀번호 입력">
					</div><br>
					
					<button class="btn btn-lg btn-primary btn-block" type="submit" value="로그인">로그인</button>
				</form>
			</div>
    	</div>
		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-3">
				<label class="checkbox">
					<input type="checkbox" name="remember" class="remember" onclick="save_idCheckCheck()" ${(cookie.save_id.value != null)?'checked':null}/>아이디 저장
				</label>
			</div>
			
		</div>	    	
	</div>

        <br><br>


</body>
</html>