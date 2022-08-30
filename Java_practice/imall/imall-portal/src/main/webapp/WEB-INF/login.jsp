<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/8/30
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>天天生鲜-登录</title>
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">
</head>
<body>
<div class="login_top clearfix">
    <a href="/" class="login_logo"><img src="/static/images/logo02.png"></a>
</div>

<div class="login_form_bg">
    <div class="login_form_wrap clearfix">
        <div class="login_banner fl"></div>
        <div class="slogan fl">日夜兼程 · 急速送达</div>
        <div class="login_form fr">
            <div class="login_title clearfix">
                <h1>用户登录</h1>
                <a href="#">立即注册</a>
            </div>
            <div class="form_input">
                <form id="formId">
                    <input type="text" name="username" class="name_input" placeholder="请输入用户名">
                    <div class="user_error">输入错误</div>
                    <input type="password" name="password" class="pass_input" placeholder="请输入密码">
                    <div class="pwd_error">输入错误</div>
                    <div class="more_input clearfix">
                        <input type="checkbox" name="">
                        <label>记住用户名</label>
                        <a href="#">忘记密码</a>
                    </div>
                    <input type="button" onclick="submitForm()" name="" value="登录" class="input_submit">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer no-mp">
    <div class="foot_link">
        <a href="#">关于我们</a>
        <span>|</span>
        <a href="#">联系我们</a>
        <span>|</span>
        <a href="#">招聘人才</a>
        <span>|</span>
        <a href="#">友情链接</a>
    </div>
    <p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
    <p>电话：010-****888    京ICP备*******8号</p>
</div>

<script>
    function submitForm() {
        // {'name':'zhansgan', 'age':23}
        // console.log($('#formId').serialize());
        $.post(
            '/user/login',
            $('#formId').serialize(),
            function(jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    var beforePath = '${beforePath}';
                    mylayer.okUrl(jsonResult.msg, beforePath);
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
