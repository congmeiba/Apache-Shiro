<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
</head>
<body>
    <font color="red">${msg}</font>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名:<input type="text" name="username" /><br/>
        密码:<input type="password" name="password" /><br/>
        记住我:<input type="checkbox" value="1" name="rememberMe"/><br/>
        <input type="submit" value="提交" />
    </form>
</body>
</html>
