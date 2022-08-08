<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/27
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form >
        <input type="text" name="name" id="nameId"/><span id="spanNameId"></span><br/>
        <input type="password" name="password"/><span id="spanPasswordId"></span><br/>
        <input type="submit" value="登录"/>
    </form>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script>
        $(function() {  //页面加载结束执行
            $('#nameId').blur(function() {  //选择id绑定失去焦点事件
                // blur本身有this
                var name = $('#nameId').val(); //$.(this).val();
                $.post(
                    '<%=request.getContextPath()%>/ajax2',  //地址
                    {'name' : name},                        //请求服务器端端数据
                    function(jsonObj) {                     //成功相应触发的函数
                        console.log(jsonObj);
                        // {exist: true, msg: '此用户已经存在，请更换一个'}
                        if (jsonObj.exist) {
                            $('#spanNameId').html(jsonObj.msg);     //获取-(普通文本 和 html标签 都可以获得)
                            $('#spanNameId').css('color', 'red');   //css修改样式
                        } else {
                            $('#spanNameId').text(jsonObj.msg);     //获取-只是针对文本内容其作用(过滤html标签)
                            $('#spanNameId').css('color', 'green'); //jquery的css方法获得样式，"行内、内部、外部" 样式均可以获得
                        }
                    },
                    'json'                                  //返回的数据类型
                );
            });
        });
    </script>
</body>
</html>