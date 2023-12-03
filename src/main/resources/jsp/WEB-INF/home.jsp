<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>YH Compuny</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>

  .container2 {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 20vh;
    font-family: 'Kalam', cursive;
    	height: 200px;
		background-image: url("/img/sa.jpg");
		background-size: cover;
		color: white;
    
  }


</style>
<script>
	window.onunload = function () { 
	    window.opener.location.reload(); 
	}
</script>
<body>

<div class="container2">
<h1>YH Compuny</h1>
<h1>Home Page</h1>
</div>
<sec:authorize access="isAnonymous()">
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">홈페이지</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/user/login" onclick="window.open('로그인', '로그인','top=200px, left=1000px, width=500px, height=800px'); return false">로그인</a>
      </li>
    </ul>
</sec:authorize>
</nav>
<sec:authorize access="isAuthenticated()" >
어서오세요 <sec:authentication property="name"/>님 환영합니다.
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">홈페이지</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
	 <li class="nav-item">
        <a class="nav-link" href="/user/update" >회원정보 수정</a>
     </li>
     <li class="nav-item">
        <a class="nav-link" href="/user/logout" >로그아웃</a>
      </li>
    </ul>
</sec:authorize> 
</nav>
<br>

<div class="container">

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">부서</h4>
    <a href="/dept/list" class="btn btn-primary">부서 정보</a>
    <a href="/dept/create" class="btn btn-primary">부서 생성</a>
    <a href="/dept/update?deptno=10" class="btn btn-primary">부서 수정</a>
    <a href="/dept/delete?deptno=10" class="btn btn-primary">부서 삭제</a>
  </div>
</div>

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">직원</h4>
    <a href="/emp/list" class="btn btn-primary">직원 정보</a>
    <a href="/emp/create" class="btn btn-primary">직원 추가</a>
    <a href="/emp/update?empno=1001" class="btn btn-primary">직원 수정</a>
    <a href="/emp/delete?empno=1001" class="btn btn-primary">직원 삭제</a>
  </div>
</div>

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">급여</h4>
    <a href="/salgrade/list" class="btn btn-primary">급여 정보</a>
    <a href="/salgrade/create" class="btn btn-primary">급여 추가</a>
    <a href="/salgrade/update?grade=1" class="btn btn-primary">급여 수정</a>
    <a href="/salgrade/delete?grade=1" class="btn btn-primary">급여 삭제</a>
  </div>
</div>

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">국가</h4>
    <a href="/country/page/1/10" class="btn btn-primary">국가 정보</a>
    <a href="/country/create" class="btn btn-primary"> 국가 추가</a>
    <a href="/country/update?" class="btn btn-primary">국가 수정</a>
    <a href="/country/delete?" class="btn btn-primary">국가 삭제</a>
  </div>
</div>

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">도시</h4>
    <a href="/city/page/1/10" class="btn btn-primary">도시 정보</a>
    <a href="/city/create" class="btn btn-primary">도시 추가</a>
    <a href="/city/update?" class="btn btn-primary">도시 수정</a>
    <a href="/city/delete?" class="btn btn-primary">도시 삭제</a>
  </div>
</div>

<div class="card m-2" >
  <div class="card-body">
    <h4 class="card-title">언어</h4>
    <a href="/language/page/1/10" class="btn btn-primary">언어 정보</a>
    <a href="/language/create" class="btn btn-primary">언어 추가</a>
    <a href="/language/update?" class="btn btn-primary">언어 수정</a>
    <a href="/language/delete?" class="btn btn-primary">언어 삭제</a>

  </div>
</div>


</div>
<div class="jumbotron text-center" style="margin-bottom:0">
  <p>YH Compuny</p>
  <p>📞 010-1234-5678</p>
  <p>🏴 경기도 부천시 xx동</p>
</div>
</body>
</html>