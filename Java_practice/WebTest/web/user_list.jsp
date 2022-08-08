<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/8/1
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="layFilter"></table>
<%--表名为layFilter--%>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
    </div>
</script>

<script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/static/mylayer.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use('table', function(){      //使用layui中的table模块
        var table = layui.table;

        table.render({      //对table进行渲染
            elem: '#test'
            ,url:'${pageContext.request.contextPath}/user?method=selectByPage'  //layui自动添加page=1&limit=10的参数
            ,toolbar: '#toolbarDemo' //开启头部工具栏
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type:'checkbox', fixed: 'left'}    //在表格前加入多选框
                ,{field:'id', title: 'ID', sort: true}
                ,{field:'name',  title: '用户名'}
                ,{field:'password', title: '密码'}
                ,{field:'age', title: '年龄'}
                ,{field:'address', title: '地址'}
                ,{field:'gender', title: '性别'}
                ,{title:'操作', toolbar: '#barDemo'}      //最后一列负责显示编辑和删除按钮
            ]]
            ,page: true
            ,id: 'tableId'  //给table加id
        });

        //监听工具条
        table.on('tool(layFilter)', function(obj){
            var data = obj.data;    //data为当前这一行数据
            if(obj.event === 'del'){    //点删除按钮执行的操作
                layer.confirm('真的删除行么', function(index){
                    $.post(
                        '${pageContext.request.contextPath}/user?method=deleteById',
                        {'id' : data.id},   //data的id
                        function(jsonObj) {
                            console.log(jsonObj);
                            if (jsonObj.code == 0) {
                                mylayer.okMsg(jsonObj.msg);
                                // 删除之后重新刷新table表格
                                table.reload('tableId');    //重载上面那个表格
                            } else {
                                mylayer.errorMsg(jsonObj.msg);
                            }
                        },
                        'json'
                    );

                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type : 2,
                    area : ['500px', '400px'],
                    //content : '${pageContext.request.contextPath}/user?method=getUserUpdatePage&id=' + data.id
                    content : '${pageContext.request.contextPath}/user_update1.jsp?id=' + data.id
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(layFilter)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    layer.open({
                        type : 2,
                        area : ['500px', '400px'],
                        content : '${pageContext.request.contextPath}/user_add.jsp'
                    });
                    break;
                case 'deleteAll':
                    var data = checkStatus.data;
                    //layer.alert(JSON.stringify(data)); 转化为json的字符串
                    var ids = new Array();
                    $(data).each(function (){
                        ids.push(this.id);
                        //组成一个由选中id的Array
                    });
                    // ids = ids.join(',');用逗号隔开转为字符串
                    layer.confirm('真的删除这几行么', function(index){
                        $.post(
                            '${pageContext.request.contextPath}/user?method=deleteAll',
                            {'ids' : ids},   //data的id
                            function(jsonObj) {
                                console.log(jsonObj);
                                if (jsonObj.code == 0) {
                                    mylayer.okMsg(jsonObj.msg);
                                    // 删除之后重新刷新table表格
                                    table.reload('tableId');    //重载上面那个表格
                                } else {
                                    mylayer.errorMsg(jsonObj.msg);
                                }
                            },
                            'json'
                        );
                    });
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
    });
</script>
</body>
</html>