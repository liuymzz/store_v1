<%@page import="entities.Order"%>
<%@page import="entities.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/admin_jsp/layui/css/layui.css" />
<script src="<%=request.getContextPath()%>/admin_jsp/js/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/admin_jsp/layui/layui.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<body>

	<%
		PageBean<Order> pageBean = (PageBean<Order>) request.getAttribute("bean");
		if (pageBean != null && pageBean.getList().size() > 0) {
	%>

	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
			<tr>

				<th>订单编号</th>
				<th>订单金额</th>
				<th>收货人</th>
				<th>订单状态</th>
				<th>订单详情</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Order order : pageBean.getList()) {
			%>
			<tr>

				<td><%=order.getOid()%></td>
				<td><%=order.getTotal()%></td>
				<td><%=order.getName()%></td>
				<td>
					<%
						if (order.getState() == 0) {
									out.print("未付款");
								}
								if (order.getState() == 1) {
									out.print("已付款");
								}
					%>
				</td>
				<td>
					<button class="layui-btn layui-btn-normal"
						onclick="detail('<%=order.getOid()%>')">订单详情</button>
				</td>
			</tr>

			<%
				}
			%>
		</tbody>
	</table>

	<%
		}
	%>

</body>

<!--分页 -->
<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
	<ul class="pagination" style="text-align: center; margin-top: 10px;">
		<!-- <li class="disabled"><a href="#" aria-label="Previous"><span
				aria-hidden="true">&laquo;</span></a></li>
		<li class="active"><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#">6</a></li>
		<li><a href="#">7</a></li>
		<li><a href="#">8</a></li>
		<li><a href="#">9</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li> -->
		
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
					href="<%=(request.getContextPath() + "/adminOrder?method=findAll&currPage=" + (currPage - 1))%>"
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
					href="<%=(request.getContextPath() + "/adminOrder?method=findAll&currPage=" + i)%>"><%=i%></a></li>
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
					href="<%=(request.getContextPath() + "/adminOrder?method=findAll&currPage=" + (currPage + 1))%>"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				<%
					}
				%>
		
	</ul>
</div>
<!-- 分页结束=======================        -->

<table class="layui-table" id="order_detail" style="display: none;">
	<colgroup>
		<col width="150">
		<col width="200">
		<col>
	</colgroup>
	<thead>
		<tr>
			<th>商品名称</th>
			<th>购买数量</th>
			<th>小计</th>
		</tr>
	</thead>
	<tbody id="order_detail_tbody">
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>

</html>

<script type="text/javascript">
	var layer;
	layui.use('layer', function() {
		layer = layui.layer;
	});

	function detail(id) {
		var $d = $('#order_detail');
		//异步查询,动态添加内容到$d中,需要先清空表格中上次添加的内容
		$('#order_detail_tbody').empty();
		$.get('<%=request.getContextPath()%>/adminOrder?method=getOrderDetail&oid='+id,
			function(data){
			$(data).each(function(){
				var s='<tr><td>'+this.product.pname+'</td>'+'<td>'+this.count+'</td>'+'<td>'+this.subtotal+'</td></tr>';
				$('#order_detail_tbody').append(s);
			});
			
			
		},"json");
		
		layer.open({
			type : 1,
			title : '订单详情',
			content : $d,
			shadeClose : true
		});
	}
</script>