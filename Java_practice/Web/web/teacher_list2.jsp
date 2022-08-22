<%@ page import="com.situ.web.util.PageInfo" %>
<%@ page import="com.situ.web.pojo.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/22
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
</head>
<body>
    <%
        PageInfo<Teacher> pageInfo = (PageInfo) request.getAttribute("pageInfo");
    %>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td>ID</td>
            <td>名字</td>
            <td>年龄</td>
            <td>地址</td>
            <td>删除</td>
            <td>编辑</td>
        </tr>
        <%
            for (Teacher teacher : pageInfo.getList()) {
        %>
                <tr>
                    <td><%=teacher.getId()%></td>
                    <td><%=teacher.getName()%></td>
                    <td><%=teacher.getAge()%></td>
                    <td><%=teacher.getAddress()%></td>
                    <td>删除</td>
                    <td>编辑</td>
                </tr>
        <%
            }
        %>

    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=<%=pageInfo.getPageNo()-1%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%
                for(int i = 1; i <= pageInfo.getTotalPage(); i++) {
            %>
                    <li><a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=<%=i%>"><%=i%></a></li>
            <%
                }
            %>
            <li>
                <a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=<%=pageInfo.getPageNo()+1%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</body>
</html>
