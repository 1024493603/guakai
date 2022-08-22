<%@ page import="com.situ.web.util.PageInfo" %>
<%@ page import="com.situ.web.pojo.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/22
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
</head>
<body>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td>ID</td>
            <td>名字</td>
            <td>年龄</td>
            <td>地址</td>
            <td>删除</td>
            <td>编辑</td>
        </tr>
        <c:forEach items="${pageInfo.list}" var="teacher">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.age}</td>
                <td>${teacher.address}</td>
                <td><a href="javascript:deleteById(${teacher.id})">删除</a></td>
                <td>编辑</td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=${pageInfo.pageNo-1}%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${pageInfo.totalPage}" var="i">
                <li><a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${i}">${i}</a></li>
            </c:forEach>
            <li>
                <a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=${pageInfo.pageNo+1}%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
    <script>
        function deleteById(id) {
            layer.confirm('您确定要删除么？', function() {
                $.post(
                    '${pageContext.request.contextPath}/teacher?method=deleteById',
                    {'id' : id},
                    function(jsonObj) {
                        // {msg: '删除成功', code: 0}
                        console.log(jsonObj);
                        if (jsonObj.code == 0) {
                            mylayer.okUrl(jsonObj.msg, '${pageContext.request.contextPath}/teacher?method=selectByPage');
                            //mylayer.okMsg(jsonObj.msg);
                            //location.href = '${pageContext.request.contextPath}/teacher?method=selectByPage'
                        } else {
                            mylayer.errorMsg(jsonObj.msg);
                        }
                    },
                    'json'
                );
            })


        }

    </script>
</body>
</html>
