<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/21
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="formId" method="post">
        用户名:<input type="text" name="name"/><br/>
        年龄:<input type="text" name="age"/><br/>
        性别:<input type="text" name="gender"/><br/>
        <input type="button" onclick="submitAdd()" value="添加"/>
    </form>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
    <script>
        function submitAdd() {
            $.post(
                '${pageContext.request.contextPath}/student/add.action',
                $('#formId').serialize(),
                function (jsonResult) {
                    console.log(jsonResult);

                    if (jsonResult.code == 0){
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
