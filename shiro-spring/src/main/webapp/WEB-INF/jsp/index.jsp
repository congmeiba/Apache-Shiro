<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE>
<html>
<head>

</head>
<body>
<h2>欢迎进入Shiro</h2>

<span>用户名:${sessionScope.User.username}</span>


<!-- hasPermission 当Subject用户权限授权码有对应的,才会显示对应的标签内容  -->
<s:hasPermission name="add">
    <h4><a href="${pageContext.request.contextPath}/shiro/insert.do">商品添加</a></h4>
</s:hasPermission>
<!-- hasRole  只有当当前Subject被分配了指定角色时， hasRole标签才会显示其包装内容。-->
<s:hasRole name="admin">
    <h4><a href="${pageContext.request.contextPath}/shiro/delete.do">商品删除</a></h4>
</s:hasRole>
<h4><a href="${pageContext.request.contextPath}/shiro/update.do">商品修改</a></h4>

</body>
</html>
