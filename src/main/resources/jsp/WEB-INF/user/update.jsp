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
<script src=/webjars/bootstrap/js/bootstrap.min.js></script>
<script src=/webjars/jquery/jquery.min.js></script>
<title>회원정보 수정</title>
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

.container a input {
	width: 100%; height: 46px;  box-sizing: border-box;
}
.container a input::-webkit-input-placeholder { font-size: 16px; color: #9fa19f; }


.container button {
	width: 100%;
	height: 56px;
	line-height: 56px;
	background: #ed1c24;
	font-size: 18px;
	color: #fff;
}

.container div a {justify-content: center;}


</style>
<body>
<h1>회원정보 수정</h1>
<hr>
<div class="container"action="/user/update" method="post">
	<h3>기본정보</h3>
	<div class="row">
		<a><input name="id" class="form-control" type="text" placeholder="아이디" value="${user.id}" disabled="disabled"/></a>
		<a><input name="id" type="hidden" value="${user.id}"/></a>
	</div>
	<div class="row">
		<a><input name="password" class="form-control" type="password" placeholder="변경할 비밀번호"/></a>
	</div>
	<div class="row">
		<a><input name="password2" class="form-control" type="password" placeholder="비밀번호 재입력"/></a>
	</div>
	<div class="row">
		<a><input name="name" class="form-control" type="text" placeholder="이름" value="${user.name}"/></a>
	</div>
	<div class="row">
		<a><input name="phoneNumber" class="form-control" type="text" placeholder="휴대전화 번호" value="${user.phoneNumber}"/></a>
	</div>
	<div>*예시:000-0000-0000</div>
	<hr>
	<div>
		<a><button>회원정보 수정</button></a>
	</div>
	<hr>
	<a class="btn btn-secondary" href="/">메인 화면으로</a>
</div>
<c:if test="${binding.hasErrors()}">
	<h2>*알림</h2>
</c:if>
<c:forEach var="g" items="${binding.globalErrors}">
	<div>${g.defaultMessage}</div>
</c:forEach>
<c:forEach var="f" items="${binding.fieldErrors}">
	<div>${f.defaultMessage}</div>	
</c:forEach>
</body>
</html>