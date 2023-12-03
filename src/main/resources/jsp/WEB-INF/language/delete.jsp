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
<title>create.jsp</title>
</head>
<body>
<h1>언어 삭제</h1>
<hr>
<a class="btn btn-success" href="/">Home</a>
<hr>
<section class="container">
	<form action="/language/delete" method="post">
		<div class="mb-3">
			<label class="form-lable mb-2" for="countryCode">countryCode<span>*</span></label>
			<input class="form-control"    id="countryCode" name="countryCode"  value="${language.countryCode}" disabled="disabled"/>
			<input class="form-control"    id="countryCode" name="countryCode" value="${language.countryCode}" type="hidden"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for="language">language<span>*</span></label>
			<input class="form-control"    id="language"  name="language"   value="${language.language}" disabled="disabled" />
			<input class="form-control"    id="language"  name="language"   value="${language.language}" type="hidden"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for="isOfficial">isOfficial</label>
			<input class="form-control"    id="isOfficial"    name="isOfficial"     value="${language.isOfficial}" disabled="disabled"/>
			<input class="form-control"    id="isOfficial"    name="isOfficial"     value="${language.isOfficial}" type="hidden"/>
		</div>
		<div class="mb-3">
			<label class="form-lable mb-2" for="percentage">percentage</label>
			<input class="form-control"    id="percentage"    name="percentage"    value="${language.percentage}" disabled="disabled"/>
			<input class="form-control"    id="percentage"    name="percentage"    value="${language.percentage}" type="hidden"/>
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