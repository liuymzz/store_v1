<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<title>新增商品</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/admin_jsp/layui/css/layui.css" media="all">
<script src="<%=request.getContextPath()%>/admin_jsp/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/admin_jsp/js/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<body>

	<ul class="layui-nav" lay-filter="">
		<li class="layui-nav-item">
			<center>新增商品</center>
		</li>
	</ul>
	<%
		if(request.getAttribute("msg")!=null){
	%>
	<blockquote class="layui-elem-quote"><%=request.getAttribute("msg") %></blockquote>
	<% 
		}
	%>
	<br />
	<br />
	<br />
	<form class="layui-form" method="post" action="<%=request.getContextPath()%>/adminProduct?method=addProduct">
		<div class="layui-form-item">
			<label class="layui-form-label">商品名称</label>
			<div class="layui-input-block">
				<input type="text" name="pname" placeholder="请输入" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">分类</label>
			<div class="layui-input-block">
				<select lay-filter="aihao">
				<%
					List<Category> list = (List<Category>)request.getAttribute("category");
				String initCid = null;
				if(list == null || list.size()<0){
					%>
					<option name="cid">没有分类</option>
					<%
					
				}else{
					initCid = list.get(0).getCid();
					for(Category category:list){
				%>
				<option value="<%=category.getCid() %>" ><%=category.getCname() %></option>
				<% 
					}
					}
				%>
				</select>
			</div>
			<input id="cid" type="text" name="cid" value="<%=initCid %>" style="display: none;">
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">是否热门</label>
			<div class="layui-input-block">
				<input type="radio" name="is_hot" value="0" title="否"
					checked="checked" /> <input type="radio" name="is_hot" value="1"
					title="是" />
			</div>
		</div>
		<label class="layui-form-label">市场价格</label>
		<div class="layui-input-block">
			<input type="text" name="market_price" placeholder="请输入市场价格"
				autocomplete="off" class="layui-input">
		</div>
		<br /> <label class="layui-form-label">商城价格</label>
		<div class="layui-input-block">
			<input type="text" name="shop_price" placeholder="请输入商城价格"
				autocomplete="off" class="layui-input">
		</div>

		<br /> <label class="layui-form-label" style="visibility: hidden;">隐藏</label>
		<input type="file" name="pimage" class="layui-upload-file" id="uploadPic" >
		<input id="pimage" type="text" name="pimage" style="display: none;">
		 <br />
		
		<label class="layui-form-label" style="visibility: hidden;">隐藏</label>
		<img id="pic" style="display: none;">
		<br/>
		<br/>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">商品描述</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" class="layui-textarea" name="pdesc"></textarea>
			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="go">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script>
	
		layui.use('form', function() {
			var form = layui.form();

			//提交监听
			form.on('submit(*)', function(data){
				alert(111)
				
				  console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
				  console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
				  console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
				});
			
			//下拉选项监听
			form.on('select(aihao)', function(data){
				$('#cid').attr('value',$('.layui-this').attr('lay-value'));
				  console.log(data.elem); //得到select原始DOM对象
				  console.log(data.value); //得到被选中的值
				  console.log(data.othis); //得到美化后的DOM对象
				});    
			
		});

		layui.use('upload', function() {
			layui.upload({
				  url: '<%=request.getContextPath() + "/" + "adminProduct?method=uploadPic"%>'
				  ,success: function(res){
				    console.log(res); //上传成功返回值，必须为json格式
				    $(".layui-box.layui-upload-button").hide();
				    $('#pic').attr('src',res.path);
				    $('#pic').show();
				    $('#pimage').attr('value',res.pName)
				  }
				}); 
						});
		
		
	</script>