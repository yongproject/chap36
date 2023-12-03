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
<title>result.jsp</title>
</head>
<style>

 .s1 {position: relative; z-index: 2;}
 .s1 h1 {
	 font-size: 40px; color:#fff;
	 text-align: center;
 		}



  body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100vh;
    background-image: url("/img/ww.jpg");
    background-size: cover;
	font-size: 18px; color:#fff;   
  }


</style>

<body>
<c:choose>
	<c:when test="${param.id ne null}">
		<h1>아이디 찾기</h1>
		<hr>
		<h3>당신의 id는 ${param.id}입니다.</h3>
	</c:when>
	<c:when test="${pw ne null}">
		<h1>비밀번호 찾기</h1>
		<hr>
		<h3> 임시 비밀번호는 ${pw} 입니다.</h3>
		<hr>
		<h3>로그인 후 비밀번호를 변경해주세요.</h3>		
	</c:when>
</c:choose>
<hr>
<a href="/user/login" class="btn btn-success">로그인 페이지로 이동</a>
</body>
</html>