<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
		<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>

		<ul class="layui-nav layui-nav-tree" lay-filter="test">
			侧边导航:
			<ul class="layui-nav layui-nav-tree layui-nav-side">
				<li class="layui-nav-item layui-nav-itemed">
					<dl class="layui-nav-child">
						<dd>
							<a href="${pageContext.request.contextPath }/adminCategory?method=findAll" target="mainFrame">分类管理</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath }/adminProduct?method=findByPage&currPage=1" target="mainFrame">商品管理</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath }/adminOrder?method=findAll&currPage=1" target="mainFrame">所有订单</a>
						</dd>
					</dl>
				</li>

			</ul>
		</ul>

	</body>

</html>