<%@page import="entities.Product"%>
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
	<br />
	<a href="${pageContext.request.contextPath }/adminProduct?method=addProductUI">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="layui-btn">添加商品</button>
	</a>
	<%
		PageBean<Product> pageBean = (PageBean<Product>) request.getAttribute("pageBean");
	%>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>是否热门</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Product product : pageBean.getList()) {
			%>
			<tr>
				<td><img
					src="<%=request.getContextPath() + "/" + product.getPimage()%>" /></td>
				<td><%=product.getPname()%></td>
				<td><%=product.getShop_price()%></td>
				<td>
					<%
						if (product.getIs_hot() == 1) {
								out.print("是");
							} else {
								out.print("否");
							}
					%>
				</td>
				<td>
					<button class="layui-btn layui-btn-danger" onclick="del('<%=product.getPid()%>')">删除</button>
					<%-- <button class="layui-btn" onclick="update('<%=product.getPid()%>')">修改</button> --%>
				</td>
			</tr>

			<%
				}
			%>
		</tbody>
	</table>

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
					href="<%=(request.getContextPath() + "/adminProduct?method=findByPage&currPage=" + (currPage - 1))%>"
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
					href="<%=(request.getContextPath() + "/adminProduct?method=findByPage&currPage=" + i)%>"><%=i%></a></li>
				<%
					}
					}
				%>

				<%
					if (currPage >= pageBean.getTotalPage()) {
				%>
				<li class="disabled"><a aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
				<%
					} else {
				%>
				<li><a
					href="<%=(request.getContextPath() + "/adminProduct?method=findByPage&currPage=" + (currPage + 1))%>"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				<%
					}
				%>
		
	</ul>
</div>
<!-- 分页结束=======================        -->


</body>



</html>

<script>
	var layer;
	layui.use('layer', function() {
		layer = layui.layer;
	});

	var form;
	layui.use('form', function() {
		form = layui.layer;
	});

	
	
	function del(id) {
		layer.confirm('真的要删除嘛?', {
			icon : 3,
			title : '删除分类',
			shadeClose : true
		}, function(index) {
			//操作
			$.get('<%=request.getContextPath()%>/adminProduct?method=del&pid='+id,function(data){
				location.reload();
			});
			layer.close(index);
		});
	}
</script>