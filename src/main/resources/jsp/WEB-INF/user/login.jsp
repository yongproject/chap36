<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/img/favicon.png">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/h-tag-font.css">
<script src=/webjars/bootstrap/js/bootstrap.min.js></script>
<script src=/webjars/jquery/jquery.min.js></script>
<title>로그인</title>


</head>
<style>

 .container {
 	width: 450px;
 	position: absolute;
 	left: 50%;
 	top: 50%; 
 	transform: translate(-50%, -50%);
 }
 
 .container h1 {
 	padding: 0 0 20px;
 	border-bottom: 2px solid #111;
 	text-align: center;
 	line-height: 1;
 }

.container p input {
	width: 100%; height: 46px;  box-sizing: border-box;
}
.container p input::-webkit-input-placeholder { font-size: 16px; color: #9fa19f; }


.container button {
	width: 100%;
	height: 56px;
	line-height: 56px;
	background: #ed1c24;
	font-size: 18px;
	color: #fff;
}


</style>
<script type="text/javascript">
	
function loginSub() {
	console.log("LOG IN TEST");
	
	$.ajax({
		type: "POST",
		url: action,
		data: form_data,
		success: function(response) {
			// login result
			console.log('response', response);
			if(response == 'success') {
				/* $("#message").html("<p style='color:green;font-weight:bold'>로그인 성공!</p>");
				$("#form1").slideUp('slow'); */
				// 여기가 비동기통신 성공한 부분.
				alert("LOGIN SUCCESS");
				location.href="/";
				window.close();
			}
			else {
				 $("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");	
		},
		error: function() {
			console.log("ajax fail");
		}
	});
	return false;	
}
</script>
<body>
<section class="container">
<h1>로그인</h1>
	 	<form action="/user/login" method="post">
	<p><input name="username" type="text" value="${param.username}" placeholder="아이디" title="아이디입력"></p>
	<p><input name="password" type="password" value="${param.password}" placeholder="비밀번호" title="비밀번호입력"></p>
	<div>
		<input name="remember-me" type="checkbox" id="chk" checked="checked"><label for="chk"> 로그인 상태 유지</label>
	</div>
			<p><button onclick="loginSub()">로그인</button></p>
	</form>
	<div class="row mt-3">
		<p class="col-4 border text-secondary text-decoration-none text-center" href="/user/create">회원가입</p>
		<p class="col-4 border text-secondary text-decoration-none text-center" href="/user/select/id">아이디 찾기</p>
		<p class="col-4 border text-secondary text-decoration-none text-center" href="/user/select/pw">비밀번호 찾기</p>
	</div>
	<c:if test="${exception ne null}">
		<span>*회원정보가 일치하지 않습니다.</span>
	</c:if>
</section>
</body>
</html>