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
</head>
<body>
    <form id="formId" method="post">
        用户名:<input type="text" name="name"/><br/>
        密码:<input type="password" name="password"/><br/>
        验证码:<input type="text" name="code">
        <img id="verifyId" onclick="refresh()" src="${pageContext.request.contextPath}/verifyCode"><br/>
        <input type="button" onclick="submitForm()" value="登陆"/>
    </form>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
    <script>
        function submitForm() {
            $.post(
                '${pageContext.request.contextPath}/user?method=login',
                $('#formId').serialize(),   //将表单内容转换成一个字符串
                function (jsonResult) {
                    console.log(jsonResult);
                    if (jsonResult.code == 0 ) {
                        mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/index.jsp');
                    } else {
                        mylayer.errorMsg(jsonResult.msg);
                    }
                },
                'json'
            )
        }
        function refresh() {
            $('#verifyId').attr('src', '${pageContext.request.contextPath}/verifyCode?' + Math.random());
            //Math.random()防止浏览器将其当作同一个指令
        }
    </script>
</body>
</html>
