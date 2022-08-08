<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/23
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/teacher?method=add" method="post">
        姓名:<input type="text" name="name"/><br/>
        年龄:<input type="text" name="age"/><br/>
        地址:<input type="text" name="address"/><br/>
        <input type="submit" value="添加"/>
    </form>
</body>
</html>
