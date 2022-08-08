<%@ page import="com.practice.web.pojo.Student" %>
<%@ page import="com.practice.web.util.StudentPageInfo" %><%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/20
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
</head>
<body>
<%--        jsp脚本，可任意写java代码--%>
<%--        request内置对象--%>
    <%
        StudentPageInfo studentPageInfo = (StudentPageInfo) request.getAttribute("studentPageInfo");

    %>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td >
                <a href="<%=request.getContextPath()%>/student_add.jsp">添加</a>
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
        <%
            for (Student student : studentPageInfo.getList()) {
        %>
                <tr>
                    <td><%=student.getId()%></td>
                    <td><%=student.getName()%></td>
                    <td><%=student.getAge()%></td>
                    <td><%=student.getGender()%></td>
<%--                    <td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
                    <td><a href="javascript:deleteById(<%=student.getId()%>)">删除</a></td>
<%--                    <td><a href="<%=request.getContextPath()%>/student_update.jsp">编辑</a></td>--%>
<%--                    这么写界面没有数据--%>
                    <td><a href="<%=request.getContextPath()%>/student?method=getStudentUpdatePage&id=<%=student.getId()%>">编辑</a></td>
                </tr>
        <%
            }
        %>
    </table>

    <script>
        function deleteById(id) {
            var isDelete = confirm("确认删除？");
            if (isDelete) {
                location.href="<%=request.getContextPath()%>/student?method=deleteById&id=" + id;
            }
        }
    </script>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <%
                    if (studentPageInfo.getPageNo() - 1 > 0) {
                %>
                <a href="<%=request.getContextPath()%>/student?method=selectAll&pageNo=<%=studentPageInfo.getPageNo() - 1%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
                <%
                    }
                %>
            </li>
            <%
                for (int i = 1; i <= studentPageInfo.getTotalPage(); i++){
            %>
                    <li><a href="<%=request.getContextPath()%>/student?method=selectAll&pageNo=<%=i%>"><%=i%></a></li>
            <%
                }
            %>
            <li>
                <%
                    if (studentPageInfo.getPageNo() + 1 <= studentPageInfo.getTotalPage()) {
                %>
                <a href="<%=request.getContextPath()%>/student?method=selectAll&pageNo=<%=studentPageInfo.getPageNo() + 1%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
                <%
                    }
                %>
            </li>
        </ul>
    </nav>
</body>
</html>
