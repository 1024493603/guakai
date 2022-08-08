<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/28
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="<%=request.getContextPath()%>/static/echarts.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
</head>
    <body>
        <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>
        <div id="main1" style="width: 600px;height:400px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var chartDom = document.getElementById('main1');
            var myChart1 = echarts.init(chartDom);

            $.post(
                '<%=request.getContextPath()%>/echars',
                function (jsonObj) {
                    console.log(jsonObj);

                    var XArray = new Array();
                    var YArray = new Array();
                    $(jsonObj).each(function () {
                        XArray.push(this.name);
                        YArray.push(this.value);
                    });

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '人数统计'
                        },
                        tooltip: {},
                        legend: {
                            data: ['人数']
                        },
                        xAxis: {
                            data: XArray
                        },
                        yAxis: {},
                        series: [
                            {
                                name: '人数',
                                type: 'bar',
                                data: YArray
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);


                    var option;

                    option = {
                        title: {
                            text: '班级人数',
                            subtext: 'Fake Data',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left'
                        },
                        series: [
                            {
                                name: '班级',
                                type: 'pie',
                                radius: '70%',
                                data: jsonObj,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    option && myChart1.setOption(option);
                },
                'json'
            );

        </script>
    </body>
</html>
