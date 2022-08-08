<%@ page import="com.practice.web.pojo.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/23
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Teacher teacher = (Teacher) request.getAttribute("teacher");
    %>
    <form action="<%=request.getContextPath()%>/teacher?method=update" method="post">
        <input type="hidden" name = "id" value="<%=teacher.getId()%>">
        姓名：<input type="text" name="name" value="<%=teacher.getName()%>"><br/>
        年龄：<input type="text" name="age" value="<%=teacher.getAge()%>"><br/>
        地址：<input type="text" name="address" value="<%=teacher.getAddress()%>"><br/>
        <input type="submit" value="更新">
    </form>
</body>
</html>
