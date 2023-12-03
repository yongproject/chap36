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
<title>list.jsp</title>
</head>
<body>
<h1>도시 조회</h1>
<hr>
<a class="btn btn-success" href="/">Home</a>
<hr>
<c:choose>
	<c:when test="${empty param.pageNum}">
		<a href="/city/list">도시 조회</a>	
	</c:when>
	<c:when test="${not empty param.pageNum}">
		<a href="/city/page/${param.pageNum}/${param.pageSize}">	
			/city/page/${param.pageNum}/${param.pageSize}
		</a>
	</c:when>
</c:choose>
<hr>
<section class="container">
	<table class="table table-striped table-bordered">
		<tbody>
		<tr>
				<tr><th>id</th><td>${city.id}</td></tr>
				<tr><th>name</th><td>${city.name}</td></tr>
				<tr><th>countryCode</th><td>${city.countryCode}</td></tr>
				<tr><th>district</th><td>${city.district}</td></tr>
				<tr><th>population</th><td>${city.population}</td></tr>
			</tr>
		</tbody>
	</table>
	<hr>
	<menu class="btn-group">
		<a href="/city/create" class="btn btn-primary">추가</a>
		<a href="/city/update?id=${city.id}" class="btn btn-secondary">수정</a>
		<a href="/city/delete?id=${city.id}" class="btn btn-danger">삭제</a>
	</menu>

</section>



</body>
</html>