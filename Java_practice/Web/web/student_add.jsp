<%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/21
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/student?method=add" method="post">
        用户名:<input type="text" name="name"/><br/>
        年龄：<input type="text" name="age"/><br/>
        性别：<input type="text" name="gender"/><br/>
        班级：<select id="banjiId" name="banjiId">
                <option>--请选择--</option>
             </select><br/>
        <input type="submit" value="添加"/>
    </form>
    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>

    <script>
        $(function() {
            $.post(
                '<%=request.getContextPath()%>/banji?method=selectAll',
                function(jsonResult) {
                    console.log(jsonResult);
                    $(jsonResult.data).each(function() {
                        $('#banjiId').append('<option value="'+this.id+'">'+this.name+'</option>');
                    });
                },
                'json'
            );

        });
    </script>
</body>
</html>
