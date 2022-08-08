<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/22
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <script src="static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
</head>
<body>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td><a href="<%=request.getContextPath()%>/teacher_add.jsp">添加</a></td>
        </tr>
        <tr>
            <td>ID</td>
            <td>名字</td>
            <td>年龄</td>
            <td>地址</td>
            <td>删除</td>
            <td>编辑</td>
        </tr>
<%--        遍历数据,items为数据集，var为数据项--%>
        <c:forEach items="${pageInfo.list}" var="teacher">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.age}</td>
                <td>${teacher.address}</td>
                <td><a href="javascript:deleteById(${teacher.id})">删除</a></td>
                <td><a href="<%=request.getContextPath()%>/teacher?method=getUpdateTeacher&id=${teacher.id}">编辑</a></td>
            </tr>
        </c:forEach>

    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <c:if test="${pageInfo.pageNo - 1 > 0}">
                    <a href="<%=request.getContextPath()%>/teacher?method=selectByPage&pageNo=${pageInfo.pageNo - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>
<%--            循环次数,var为循环变量--%>
            <c:forEach begin="1" end="${pageInfo.totalPage}" var="i">
                <li><a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${i}">${i}</a></li>
            </c:forEach>


            <li>

                <c:if test="${pageInfo.pageNo + 1 <= pageInfo.totalPage}">
                    <a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${pageInfo.pageNo + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </c:if>
            </li>
        </ul>
    </nav>
    <script>
        function deleteById(id) {
            <%--var isDelete = confirm("您要删除吗？")--%>
            <%--if (isDelete) {--%>
            <%--    location.href="<%=request.getContextPath()%>/teacher?method=deleteById&id=" + id;--%>
            <%--}--%>
            layer.confirm("确定删除?",function () {
                $.post(
                    '${pageContext.request.contextPath}/teacher?method=deleteById',
                    {"id" : id},
                    function (jsonResult) {
                        console.log(jsonResult);
                        if (jsonResult.code == 0) {
                            mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/teacher?method=selectByPage')
                        } else {
                            mylayer.errorMsg(jsonResult.msg);
                        }
                    },
                    'json'
                );
            });
        };
    </script>
</body>
</html>
