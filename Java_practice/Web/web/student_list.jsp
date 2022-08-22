<%@ page import="java.util.List" %>
<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="com.situ.web.pojo.vo.StudentBanjiVO" %>
<%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/20
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
</head>
<body>
    <%--${list}--%>
    <%
        // jsp脚本，可以任意写java代码
        // request内置对象
        List<StudentBanjiVO> list = (List<StudentBanjiVO>) request.getAttribute("list");
        //List<Student> list = (List<Student>) session.getAttribute("list");

    %>
    <a href="<%=request.getContextPath()%>/student_add.jsp">添加</a>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td>ID</td>
            <td>名字</td>
            <td>年龄</td>
            <td>性别</td>
            <td>班级名称</td>
            <td>删除</td>
            <td>编辑</td>
        </tr>
        <%
            for (StudentBanjiVO studentBanjiVO : list) {

        %>
                 <tr>
                    <td><%=studentBanjiVO.getId()%></td>
                    <td><%=studentBanjiVO.getName()%></td>
                    <td><%=studentBanjiVO.getAge()%></td>
                    <td><%=studentBanjiVO.getGender()%></td>
                    <td><%=studentBanjiVO.getBanjiName()%></td>
                    <td><a href="javascript:deleteById(<%=studentBanjiVO.getId()%>)">删除</a></td>
                     <%--td><a href="<%=request.getContextPath()%>/student_update.jsp">编辑</a></td>--%>
                     <td><a href="<%=request.getContextPath()%>/student?method=getStudentUpdatePage&id=<%=studentBanjiVO.getId()%>">编辑</a></td>
                </tr>
        <%
            }
        %>
    </table>

    <script>
        function deleteById(id) {
            var isDelete = confirm('您确认要删除么？');
            if (isDelete) {
                location.href = '<%=request.getContextPath()%>/student?method=deleteById&id=' + id;
            }
        }
    </script>
</body>
</html>
