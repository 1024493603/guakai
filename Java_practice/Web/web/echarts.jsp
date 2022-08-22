<%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/28
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script src="<%=request.getContextPath()%>/static/echarts.min.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.post(
            '<%=request.getContextPath()%>/echarts',
            function(jsonObj) {
                console.log(jsonObj);
                // [{name: 'java1807', count: 4},{name: 'java1812', count: 6}]
                var xArray = new Array();
                var yArray = new Array();
                $(jsonObj).each(function() {
                    xArray.push(this.name);
                    yArray.push(this.value);
                });
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '班级人数统计'
                    },
                    tooltip: {},
                    legend: {
                        data: ['人数']
                    },
                    xAxis: {
                        data: xArray
                    },
                    yAxis: {},
                    series: [
                        {
                            name: '人数',
                            type: 'bar',
                            data: yArray
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },
            'json'
        );


    </script>
</body>
</html>
