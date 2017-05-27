<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>分类</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/admin_jsp/layui/css/layui.css" />
<script src="<%=request.getContextPath()%>/admin_jsp/layui/layui.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/admin_jsp/js/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<body>

	<br /> &nbsp;&nbsp;&nbsp;&nbsp;
	<button class="layui-btn" onclick="add()">添加分类</button>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>分类编号</th>
				<th>分类名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list }" >
				<tr>
					<td>${c.cid }</td>
					<td>${c.cname }</td>
					<td>
						<button class="layui-btn layui-btn-danger"
							onclick="del('${c.cid }')">删除</button>
						<button class="layui-btn" onclick="update('${c.cid }')">修改</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

</html>

<script type="text/javascript">
	var layer;
	layui.use('layer', function() {
		layer = layui.layer;
	});

	function add() {
		layer.prompt({
			title : '请输入分类名称',
			shadeClose : true
		}, function(value, index, elem) {
			//进行添加操作
			$.get("<%=request.getContextPath()%>/adminCategory?method=add&cname="+value);
			location.reload();
			layer.close(index);
		});
	}

	function del(id) {
		layer.confirm('真的要删除嘛?', {
			icon : 3,
			title : '删除分类',
			shadeClose : true
		}, function(index) {
			//进行确认后的操作
			$.get("<%=request.getContextPath()%>/adminCategory?method=del&cid="+id);
			location.reload();
			layer.close(index);
		});
	}

	function update(id) {
		layer.prompt({
			title : '请输入新的分类名称',
			shadeClose : true
		}, function(value, index, elem) {
			//进行确认后的操作
			$.get("<%=request.getContextPath()%>/adminCategory?method=update&cid="+id+"&cname="+value);
			location.reload();
			layer.close(index);
		});
	}
</script>