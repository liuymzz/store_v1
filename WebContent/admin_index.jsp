<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>panda商城后台登录页面</title>
  
  
  <link rel='stylesheet prefetch' href="<%=request.getContextPath() %>/scss/bootstrap.min.css">

      <link rel="stylesheet" href="<%=request.getContextPath() %>/scss/style.css">

  
</head>

<body>
    <div class="wrapper">
    <form class="form-signin" method="post" action="${pageContext.request.contextPath }/admin?method=login">       
      <h2 class="form-signin-heading">商城后台登录页面</h2>
      <input type="text" class="form-control" name="username" placeholder="用户名" required="" autofocus="" />
      <input type="password" class="form-control" name="password" placeholder="密码" required=""/>      
      <font color="red">${msg }</font>
      <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>   
    </form>
  </div>
  
  
</body>
</html>
