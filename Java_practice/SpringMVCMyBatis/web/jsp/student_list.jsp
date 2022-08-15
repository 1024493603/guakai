<%@ page import="com.practice.ssm.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/8/11
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
<table class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td >
            <a href="<%=request.getContextPath()%>/jsp/student_add.jsp">添加</a>
        </td>
    </tr>
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>年龄</td>
        <td>性别</td>
        <td>删除</td>
        <td>编辑</td>
    </tr>
    <c:forEach items="${pageInfo.list}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.gender}</td>
            <td><a href="javascript:deleteById(${student.id})">删除</a></td>
            <td><a href="${pageContext.request.contextPath}/student/selectById.action?id=${student.id}">编辑</a></td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteById(id) {
        var isDelete = confirm("确认删除？");
        if (isDelete) {
            location.href="<%=request.getContextPath()%>/student/deleteById.action?&id=" + id;
        }
    }
</script>

<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <c:if test="${pageInfo.pageNo - 1 > 0}">
                <a href="<%=request.getContextPath()%>/student/selectByPage.action?pageNo=${pageInfo.pageNo - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
        </li>
        <c:forEach begin="1" end="${pageInfo.totalPage}" var="i">
            <li><a href="<%=request.getContextPath()%>/student/selectByPage.action?pageNo=${i}">${i}</a></li>
        </c:forEach>
        <li>
            <c:if test="${pageInfo.pageNo + 1 <= pageInfo.totalPage}">
                <a href="<%=request.getContextPath()%>/student/selectByPage.action?pageNo=${pageInfo.pageNo + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>
</body>
</html>
