<%@page import="java.util.ArrayList"%>
<%@page import="entities.PageBean"%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>

	<!-- 静态包含head.jsp页面 -->
	<%@include file="/jsp/head.jsp"%>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>">首页</a></li>
			</ol>
		</div>


		<%
			PageBean<Product> pageBean = (PageBean) request.getAttribute("pageBean");
			if (pageBean.getList().size() > 0 && pageBean.getList() != null) {
				for (Product product : pageBean.getList()) {
		%>

		<div class="col-md-2">
			<a
				href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>">
				<img
				src="${pageContext.request.contextPath}/<%=product.getPimage() %>"
				width="170" height="170" style="display: inline-block;">
			</a>
			<p>
				<a
					href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>"
					style='color: green'> <%
 	if (product.getPname().length() > 9) {
 				out.print(product.getPname().substring(0, 9) + "...");
 			} else {
 				out.print(product.getPname());
 			}
 %>
				</a>
			</p>
			<p>
				<font color="#FF0000">商城价：&yen;<%=product.getShop_price()%></font>
			</p>
		</div>
		<%
			}
			} else {
				out.print("<h1>没有数据</h1>");
			}
		%>



	</div>

	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">

			<%
				if (pageBean.getCurrPage() == 1) {
			%>
			<li class="disabled"><a aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
			<%
				} else {
			%>
			<li><a
				href="<%=request.getContextPath()%>/product?method=findByPage&cid=
				<%=request.getParameter("cid")%>&currPage=<%=pageBean.getCurrPage() - 1%>"
				aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>
			<%
				}
				int start = pageBean.getCurrPage() < 5 ? 1 : pageBean.getCurrPage() - 5;
				for (int i = start; i < start + 10 && i <= pageBean.getTotalPage(); i++) {
					if (pageBean.getCurrPage() == i) {
			%>
			<li class="active"><a><%=i%></a></li>
			<%
				} else {
			%>

			<li><a
				href="<%=request.getContextPath()%>/product?method=findByPage&cid=<%=request.getParameter("cid")%>&currPage=<%=i%>"><%=i%></a></li>

			<%
				}
				}
				if (pageBean.getCurrPage() == pageBean.getTotalPage()) {
			%>
			<li class="disabled"><a aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
			<%
				} else {
			%>
			<li><a
				href="<%=request.getContextPath()%>/product?method=findByPage&cid=
				<%=request.getParameter("cid")%>&currPage=<%=pageBean.getCurrPage() + 1%>"
				aria-label="Previous"><span aria-hidden="true">&raquo;</span> </a></li>
			<%
				}
			%>



		</ul>
	</div>
	<!-- 分页结束=======================        -->

	<!--
       		商品浏览记录:
        -->
	<div
		style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px"微软雅黑 ";">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">

				<%
					List<Product> hList = (List<Product>) request.getAttribute("hList");
					for (Product product : hList) {
				%>
				<a
					href="<%=request.getContextPath()%>/product?method=getByid&pid=<%=product.getPid()%>"><li
					style="width: 150px; height: 216; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;"><img
						src="${pageContext.request.contextPath}/<%=product.getPimage() %>"
						width="130px" height="130px" />
						<%
							if (product.getPname().length() > 9) {
									out.print(product.getPname().substring(0, 9) + "...");
								} else {
									out.print(product.getPname());
								}
						%></li></a>

				<%
					}
				%>

			</ul>

		</div>
	</div>
	<%-- 静态包含尾部内容 --%>
	<%@include file="/jsp/tail.jsp"%>

</body>

</html>