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
<link rel="icon" href="favicon.png">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src=/webjars/bootstrap/js/bootstrap.min.js></script>
<script src=/webjars/jquery/jquery.min.js></script>
<title>pw.jsp</title>
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
  
  .container button {
	width: 35%;
	height: 35px;
	line-height: 30px;
	background: #ed1c24;
	font-size: 18px;
	color: #fff;

}


</style>
<div class="s1">
<h1>비밀번호 찾기</h1>
</div>
<hr>
<body>
<form action="/user/select/pw" method="post" class="container">
	<input type="text" name="id" class="form-control" placeholder="아이디를 입력해주세요.">
	<input type="text" name="name" class="form-control" placeholder="이름을 입력해주세요.">
	<input type="text" name="phoneNumber" class="form-control" placeholder="전화번호를 입력해주세요.">
	<div>*예시:000-0000-0000</div>
	<a><button>비밀번호 찾기</button></a>
	<hr>
	<a class="btn btn-secondary" href="/user/login">뒤로 가기</a>
<c:if test="${binding.hasErrors()}">
	<h2>다시 확인해주세요</h2>
</c:if>
<c:forEach var="g" items="${binding.globalErrors}">
	<div>${g.defaultMessage}</div>
</c:forEach>
<c:forEach var="f" items="${binding.fieldErrors}">
	<div>${f.defaultMessage}</div>	
</c:forEach>	
</form>
</body>
</html>