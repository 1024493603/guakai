<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/8/11
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/student/deleteAll.action">
    <table border="1">
        <tr>
            <th>编号</th>
            <th>姓名</th>
        </tr>
        <tr>
            <td><input type="checkbox" name="ids" value="1"/></td>
            <td>张三</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="ids" value="2"/></td>
            <td>李四</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="ids" value="3"/></td>
            <td>王五</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="ids" value="4"/></td>
            <td>赵六</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>
</html>
