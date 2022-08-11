<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/8/10
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/student/add.action" method="post">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    性别:<input type="text" name="gender"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
