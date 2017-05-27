<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WEB01</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>

<body>
	<div class="container-fluid">

		<!-- 静态包含head.jsp页面 -->
		<%@include file="/jsp/head.jsp"%>

		<div class="container-fluid">
			<div class="main_con">
				<h1>${msg}</h1>
			</div>
		</div>

	</div>
	<div class="container-fluid">
		<%-- 静态包含尾部内容 --%>
		<%@include file="/jsp/tail.jsp"%>
	</div>
	</div>

</body>

</html>