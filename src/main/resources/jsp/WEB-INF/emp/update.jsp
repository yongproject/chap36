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
<title>update.jsp</title>
</head>
<body>
<h1>직원 수정</h1>
<hr>
<a class="btn btn-success" href="/">Home</a>
<hr>
<section class="container">
	<form action="/emp/update" method="post">
		<div class="mb-3">
			<label class="form-lable mb-2" for="empno">empno<span>*</span></label>
			<input class="form-control"    id="empno" name="empno" value="${emp.empno}" disabled="disabled"/>
			<input class="form-control"    id="empno" name="empno" value="${emp.empno}" type="hidden"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for="ename">ename<span>*</span></label>
			<input class="form-control"    id="ename"  name="ename"  placeholder="직원이름을 입력하세요." value="${emp.ename}"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for="gender">gender</label>
			<input class="form-control"    id="gender"    name="gender"    placeholder="성별을 입력하세요(M/F)." value="${emp.gender}"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for=job>job</label>
			<input class="form-control"    id="job"    name="job"    placeholder="직책을 입력하세요." value="${emp.job}"/>
		</div><div class="mb-3">
			<label class="form-lable mb-2" for="mgr">mgr</label>
			<input class="form-control"    id="mgr"    name="mgr"    placeholder="직장상사의 사원번호를 입력하세요." value="${emp.mgr}"/>
		</div><div class="mb-3">
			<label class="form-lable mb-2" for="hiredate">hiredate</label>
			<input class="form-control"    id="hiredate"    name="hiredate"    placeholder="입사일을 입력하세요(0000-00-00)." value="${emp.hiredate}"/>
		</div><div class="mb-3">
			<label class="form-lable mb-2" for="sal">sal</label>
			<input class="form-control"    id="sal"    name="sal"    placeholder="연봉을 입력하세요." value="${emp.sal}"/>
		</div><div class="mb-3">
			<label class="form-lable mb-2" for="comm">comm</label>
			<input class="form-control"    id="comm"    name="comm"    placeholder="성과금을 입력하세요." value="${emp.comm}"/>
		</div><div class="mb-3">
			<label class="form-lable mb-2" for="deptno">deptno</label>
			<input class="form-control"    id="deptno"    name="deptno"    placeholder="부서번호를 입력하세요." value="${emp.deptno}"/>
		</div>
		<button class="btn btn-primary" type="submit">Submit</button>
	</form>
</section>
<c:if test="${binding.hasErrors()}">
	<h2>Error Message</h2>
</c:if>
<c:forEach var="g" items="${binding.globalErrors}">
	<div>${g.code}</div>
	<div>${g.defaultMessage}</div>
</c:forEach>
<c:forEach var="f" items="${binding.fieldErrors}">
	<div>${f.field}</div>
	<div>${f.defaultMessage}</div>	
</c:forEach>

</body>
</html>