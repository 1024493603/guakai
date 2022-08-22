<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/25
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="header.jsp"%>
</head>
<body>
    <form id="formId" method="post">
        用户名:<input type="text" name="name"/><br/>
        密码:<input type="password" name="password"/><br/>
        验证码:<input type="text" name="code">
        <img id="verifyId" onclick="refresh()" src="../auth/code"><br/>
        <input type="button" onclick="submitForm()" value="登陆"/>
    </form>

    <script>
        function submitForm() {
            $.post(
                '${pageContext.request.contextPath}/user/login',
                $('#formId').serialize(),   //将表单内容转换成一个字符串
                function (jsonResult) {
                    console.log(jsonResult);
                    if (jsonResult.code == 0 ) {
                        mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/');
                    } else {
                        mylayer.errorMsg(jsonResult.msg);
                    }
                },
                'json'
            )
        }
        function refresh() {
            $('#verifyId').attr('src', '${pageContext.request.contextPath}/auth/code?' + Math.random());
            //Math.random()防止浏览器将其当作同一个指令
        }
    </script>
</body>
</html>
