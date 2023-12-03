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
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src=/webjars/bootstrap/js/bootstrap.min.js></script>
<script src=/webjars/jquery/jquery.min.js></script>
<title>create.jsp</title>
</head>
<style>
 .s1 {position: relative; z-index: 2;}
 .s1 h1 {
	 font-size: 40px; 
	 text-align: center;
 		}

  body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100vh;
    background-size: cover;
	font-size: 18px;   
	width: 450px;
 	position: absolute;
 	left: 50%;
 	top: 50%; 
 	transform: translate(-50%, -50%);
  }

 .container div {
 	width: 100%; height: 46px;  box-sizing: border-box;
 }

.container button {
	width: 100%;
	height: 56px;
	line-height: 56px;
	background: #ed1c24;
	font-size: 18px;
	color: #fff;

}




</style>
<body>
<div class="s1">
<h1>회원 가입</h1>
</div>
<hr>
<form class="container"action="/user/create" method="post">
	<h3>정보</h3>
	<div class="row">
		<div class="col-8"><input name="id" class="form-control" type="text" placeholder="아이디" value="${user.id}"/></div>
	</div>
	<div class="row">
		<div class="col-8"><input name="password" class="form-control" type="password" placeholder="비밀번호"/></div>
	</div>
	<div class="row">
		<div class="col-8"><input name="password2" class="form-control" type="password" placeholder="비밀번호 확인"/></div>
	</div>
	<div class="row">
		<div class="col-8"><input name="name" class="form-control" type="text" placeholder="이름" value="${user.name}"/></div>
	</div>
	<div class="row">
		<div class="col-8"><input name="phoneNumber" class="form-control" type="text" placeholder="휴대전화 번호" value="${user.phoneNumber}"/></div>
	</div>
	<div>
				<a><button>회원가입</button></a>
	</div>
	<hr>
	<a class="btn btn-secondary" href="/user/login">뒤로 가기</a>
</form>
<c:if test="${binding.hasErrors()}">
	<h2>확인해주세요</h2>
</c:if>
<c:forEach var="g" items="${binding.globalErrors}">
	<div>${g.defaultMessage}</div>
</c:forEach>
<c:forEach var="f" items="${binding.fieldErrors}">
	<div>${f.defaultMessage}</div>	
</c:forEach>
</body>
</html>