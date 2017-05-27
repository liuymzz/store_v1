<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style>
			body {
				SCROLLBAR-ARROW-COLOR: #ffffff;
				SCROLLBAR-BASE-COLOR: #dee3f7;
			}
		</style>
	</head>

	<frameset rows="50,*" frameborder=0 border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath }/admin_jsp/top.jsp" name="topFrame" scrolling="NO" noresize>
			<frameset cols="200,*" frameborder="0" border="0" framespacing="0">
				<frame src="${pageContext.request.contextPath }/admin_jsp/left.jsp" name="leftFrame" noresize scrolling="YES">
					<frame src="${pageContext.request.contextPath }/adminCategory?method=findAll" name="mainFrame">
			</frameset>
	</frameset>

</html>