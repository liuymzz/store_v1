<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>熊猫商城</title>
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


		<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：轮播条
            -->
		<div class="container-fluid">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/img/1.jpg">
						<div class="carousel-caption"></div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/2.jpg">
						<div class="carousel-caption"></div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/3.jpg">
						<div class="carousel-caption"></div>
					</div>
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>

		<%
			List<Product> hotList = (List<Product>) request.getAttribute("hotList");
			List<Product> newList = (List<Product>) request.getAttribute("newList");
		%>
		<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：商品显示
            -->
		<div class="container-fluid">
			<div class="col-md-12">
				<h2>
					热门商品&nbsp;&nbsp;<img
						src="${pageContext.request.contextPath}/img/title2.jpg" />
				</h2>
			</div>
			<div class="col-md-2"
				style="border: 1px solid #E7E7E7; border-right: 0; padding: 0;">
				<img src="${pageContext.request.contextPath}/products/hao/big01.jpg"
					width="205" height="404" style="display: inline-block;" />
			</div>
			<div class="col-md-10">
				<div class="col-md-6"
					style="text-align: center; height: 200px; padding: 0px;">
					<a href="product_info.htm"> <img
						src="${pageContext.request.contextPath}/products/hao/middle01.jpg"
						width="516px" height="200px" style="display: inline-block;">
					</a>
				</div>

				<%
				if(hotList != null && hotList.size()>0){
					for (Product product : hotList) {
				%>
				<div class="col-md-2"
					style="text-align: center; height: 200px; padding: 10px 0px;">
					<a
						href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>">
						<img
						src="${pageContext.request.contextPath}/<%=product.getPimage() %>"
						width="130" height="130" style="display: inline-block;">
					</a>
					<p>
						<a
							href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>"
							style='color: #666'> <%
 	if (product.getPname().length() > 9) {
 			out.print(product.getPname().substring(0, 9) + "...");
 		} else {
 			out.print(product.getPname());
 		}
 %>
						</a>
					</p>
					<p>
						<font color="#E4393C" style="font-size: 16px">&yen;<%=product.getShop_price()%></font>
					</p>
				</div>
				<%
					}
				}
				%>

			</div>
		</div>
		<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：广告部分
            -->
		<div class="container-fluid">
			<img src="${pageContext.request.contextPath}/products/hao/ad.jpg"
				width="100%" />
		</div>
		<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：商品显示
            -->
		<div class="container-fluid">
			<div class="col-md-12">
				<h2>
					最新商品&nbsp;&nbsp;<img
						src="${pageContext.request.contextPath}/img/title2.jpg" />
				</h2>
			</div>
			<div class="col-md-2"
				style="border: 1px solid #E7E7E7; border-right: 0; padding: 0;">
				<img src="${pageContext.request.contextPath}/products/hao/big01.jpg"
					width="205" height="404" style="display: inline-block;" />
			</div>
			<div class="col-md-10">
				<div class="col-md-6"
					style="text-align: center; height: 200px; padding: 0px;">
					<a href="product_info.htm"> <img
						src="${pageContext.request.contextPath}/products/hao/middle01.jpg"
						width="516px" height="200px" style="display: inline-block;">
					</a>
				</div>

				<%
				if(newList != null && newList.size()>0){
					for (Product product : newList) {
				%>
				<div class="col-md-2"
					style="text-align: center; height: 200px; padding: 10px 0px;">
					<a
						href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>">
						<img
						src="${pageContext.request.contextPath}/<%=product.getPimage() %>"
						width="130" height="130" style="display: inline-block;">
					</a>
					<p>
						<a
							href="${pageContext.request.contextPath}/product?method=getByid&pid=<%=product.getPid() %>"
							style='color: #666'>
							<%
								if (product.getPname().length() > 9) {
										out.print(product.getPname().substring(0, 9) + "...");
									} else {
										out.print(product.getPname());
									}
							%>
						</a>
					</p>
					<p>
						<font color="#E4393C" style="font-size: 16px">&yen;<%=product.getShop_price()%></font>
					</p>
				</div>

				<%
					}
				}
				%>

			</div>
		</div>
		<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：页脚部分
            -->
		<div class="container-fluid">
			<%-- 静态包含尾部内容 --%>
			<%@include file="/jsp/tail.jsp"%>
		</div>
	</div>
</body>

</html>