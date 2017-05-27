<%@page import="entities.OrderItem"%>
<%@page import="java.util.List"%>
<%@page import="entities.Order"%>
<%@page import="entities.PageBean"%>
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

	</nav>

	<div class="container">
		<div class="row">

			<%
				PageBean<Order> pageBean = (PageBean) request.getAttribute("pageBean");
				List<Order> oList = pageBean.getList();
				if (pageBean.getList().size() > 0) {
			%>

			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>我的订单</strong>
				<table class="table table-bordered">
					<%
						for (Order order : oList) {
					%>
					<tbody>
						<tr class="success">
							<th colspan="5">订单编号:<%=order.getOid()%></th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<%
							List<OrderItem> oiList = order.getItems();
									for (OrderItem oi : oiList) {
						%>
						<tr class="active">
							<td width="60" width="40%"><input type="hidden" name="id"
								value="22"> <img
								src="<%=request.getContextPath() + "/" + oi.getProduct().getPimage()%>"
								width="70" height="60"></td>
							<td width="30%"><a
								href="<%=request.getContextPath() + "/product?method=getByid&pid=" + oi.getProduct().getPid()%>"
								target="_blank"> <%=oi.getProduct().getPname()%></a></td>
							<td width="20%">￥<%=oi.getProduct().getShop_price()%></td>
							<td width="10%"><%=oi.getCount()%></td>
							<td width="15%"><span class="subtotal">￥<%=oi.getSubtotal()%></span></td>
						</tr>
						<%
							}
						%>
					</tbody>

					<%
						}
					%>

				</table>
			</div>
			

		</div>
		<div style="text-align: center;">
			<ul class="pagination">

				<%
					int currPage = Integer.parseInt(request.getParameter("currPage"));
					int start = 1;
					if (currPage > 5) {
						start = currPage - 5;
					}

					if (currPage == 1) {
				%>

				<li class="disabled"><a aria-label="Previous"><span
						aria-hidden="true">&laquo;</span></a></li>
				<%
					} else {
				%>
				<li><a
					href="<%=(request.getContextPath() + "/order?method=findByPage&currPage=" + (currPage - 1))%>"
					aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<%
					}

					for (int i = start; i <= (start + 10) && i <= pageBean.getTotalPage(); i++) {
						if (currPage == i) {
				%>
				<li class="active"><a><%=i%></a></li>
				<%
					} else {
				%>
				<li><a
					href="<%=(request.getContextPath() + "/order?method=findByPage&currPage=" + i)%>"><%=i%></a></li>
				<%
					}
					}
				%>

				<%
					if (currPage == pageBean.getTotalPage()) {
				%>
				<li class="disabled"><a aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
				<%
					} else {
				%>
				<li><a
					href="<%=(request.getContextPath() + "/order?method=findByPage&currPage=" + (currPage + 1))%>"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				<%
					}
				%>



			</ul>
		</div>
		
		<%
				}else{
					
					%>
					<h1>没有数据~~~</h1>
					<%
				}
			%>
	</div>

	<%-- 静态包含尾部内容 --%>
	<%@include file="/jsp/tail.jsp"%>
</body>

</html>