<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/21
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
</head>
<body>
    <form id="formId" method="post">
        <input type="hidden" name="id" value="${student.id}"/>
        用户名:<input type="text" name="name" value="${student.name}"/><br/>
        年龄：<input type="text" name="age" value="${student.age}"/><br/>
        性别：<input type="text" name="gender" value="${student.gender}"/><br/>
        <input type="button" onclick="submitUpdate()" value="修改"/>
    </form>

    <script>
        function submitUpdate() {
            $.post(
                '${pageContext.request.contextPath}/student/update.action',
                $('#formId').serialize(),
                function (jsonResult) {
                    console.log(jsonResult);

                    if (jsonResult.code == 0) {
                        mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/student/selectByPage.action');
                    } else {
                        mylayer.errorMsg(jsonResult.msg);
                    }
                },
                'json'
            );
        }
    </script>
</body>
</html>