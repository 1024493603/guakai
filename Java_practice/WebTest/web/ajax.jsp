<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/27
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <button onclick="ajaxGet()">Ajax Get</button><br/>
    <button onclick="ajaxPost()">Ajax Post</button><br/>
    <button onclick="ajaxTest()">Ajax</button><br/>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script>
        function ajaxGet() {
            // $.get(url, [data], [callback], [type])
            $.get(
                '<%=request.getContextPath()%>/ajax',
                {'name': '张三'},
                function(jsonObj) {     //函数括号内为后台回调数据类型
                    console.log(jsonObj);
                },
                'json'
            );
        }

        function ajaxPost() {
            // $.get(url, [data], [callback], [type])
            $.post(
                '<%=request.getContextPath()%>/ajax',
                {'name': '张三11'},
                function(jsonObj) {
                    console.log(jsonObj);
                },
                'json'
            );
        }

        function ajaxTest() {
            // $.ajax( { option1:value1,option2:value2... } );
            $.ajax({
                async : true,
                url : '<%=request.getContextPath()%>/ajax',
                type : 'POST',  //前台为POST，调用后台doPost
                data : {'name' : '赵六'},
                dataType : 'json',
                success : function(jsonObj) {
                    console.log(jsonObj);
                }
            });
        }
    </script>
</body>

</html>