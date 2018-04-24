<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>

</head>
<body>
    <h2>欢迎进入Shiro</h2>

    <span >用户名:${sessionScope.username}</span>

    <h4><a href="${pageContext.request.contextPath}/insert.jsp">商品添加</a></h4>
    <h4><a href="${pageContext.request.contextPath}/delete.jsp">商品删除</a></h4>
    <h4><a href="${pageContext.request.contextPath}/update.jsp">商品修改</a></h4>

</body>
</html>
