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
<title>page.jsp</title>
</head>
<body>
<h1>도시 페이지</h1> 
<hr>
<a class="btn btn-success" href="/">Home</a>
<hr>
<div class="container">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>countryCode</th>
				<th>district</th>
				<th>population</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.id}</td>
				<td><a href="/city/detail/${e.id}?pageNum=${paging.pageNum}&pageSize=${paging.pageSize}">${e.name}</a></td>
				<td>${e.countryCode}</td>
				<td>${e.district}</td>
				<td align="right">
					<fmt:formatNumber pattern="###,###,###,###" value="${e.population}"/>
				</td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
		<ul class="pagination justify-content-center" style="margin:20px 0">
	<c:choose>
		<c:when test="${paging.navigateFirstPage eq 1}">
			<li class="page-item disabled"><a class="page-link" href="/city/page/1/${paging.pageSize}">&lt;&lt;</a></li>		
		</c:when>
		<c:otherwise>		
			<li class="page-item"><a class="page-link" href="/city/page/1/${paging.pageSize}">&lt;&lt;</a></li>		
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${paging.navigateFirstPage eq 1}">
			<li class="page-item disabled"><a class="page-link" href="/city/page/1/${paging.pageSize}">Previous</a></li>		
		</c:when>
		<c:otherwise>		
			<li class="page-item"><a class="page-link" href="/city/page/${paging.navigateFirstPage-1}/${paging.pageSize}">Previous</a></li>		
		</c:otherwise>
	</c:choose>
	<c:forEach var="n" items="${paging.navigatepageNums}">
		<c:choose>
			<c:when test="${n eq paging.pageNum}">
				<li class="page-item active"><a class="page-link" href="/city/page/${n}/${paging.pageSize}">${n}</a></li>		
			</c:when>
			<c:otherwise>			
				<li class="page-item"><a class="page-link" href="/city/page/${n}/${paging.pageSize}">${n}</a></li>		
			</c:otherwise>
		</c:choose>		
	</c:forEach>
	<c:choose>	
		<c:when test="${paging.navigateLastPage eq paging.pages}">
			<li class="page-item disabled"><a class="page-link" href="/city/page/${paging.navigateLastPage+1}/${paging.pageSize}">Next</a></li>		
		</c:when>
		<c:otherwise>		
			<li class="page-item"><a class="page-link" href="/city/page/${paging.navigateLastPage+1}/${paging.pageSize}">Next</a></li>		
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${paging.navigateLastPage eq paging.pages}">
			<li class="page-item disabled"><a class="page-link" href="/city/page/${paging.pages}/${paging.pageSize}">>></a></li>		
		</c:when>
		<c:otherwise>		
			<li class="page-item"><a class="page-link" href="/city/page/${paging.pages}/${paging.pageSize}">>></a></li>		
		</c:otherwise>
	</c:choose>
	</ul>
</div>
</body>
</html>