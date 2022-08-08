<%--
  Created by IntelliJ IDEA.
  User: 10244
  Date: 2022/7/28
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    省份:<select id="provinceId">
        <option>----请选择省份----</option>
    </select>
    城市:<select id="cityId">
        <option>----请选择城市----</option>
    </select>
    区县:<select id="areaId">
        <option>----请选择区县----</option>
    </select>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
    <script>
        $(function () {
            <%--$.post(--%>
            <%--    '<%=request.getContextPath()%>/ajax3?method=selectProvince',--%>
            <%--    function (jsonObj) {--%>
            <%--      console.log(jsonObj);--%>
            <%--        // [{id: 510000, province: '四川省'},{id: 520000, province: '贵州省'}]--%>
            <%--        /*for (var i = 0; i < jsonObj.length; i++) {--%>

            <%--        }*/--%>
            <%--        //遍历所有省并添加到选项中--%>
            <%--        $(jsonObj).each(function () {--%>
            <%--            //<option value="5100">四川省</option>--%>
            <%--            $('#provinceId').append('<option value="'+this.id+'">'+this.province+'</option>');--%>
            <%--        });--%>
            <%--    },--%>
            <%--    'json'--%>
            <%--);--%>

            $.post(
                '<%=request.getContextPath()%>/ajax3?method=selectProvince',
                function (jsonResult) {
                    console.log(jsonResult);
                    //{code: 0, msg: null, data: Array(34), ok: true}
                    //<option value="5100">四川省</option>
                    $(jsonResult.data).each(function () {
                        $('#provinceId').append('<option value="'+this.id+'">'+this.province+'</option>');
                    });
                },
                'json'
            );

            $('#provinceId').change(function () {
                var provinceId = $('#provinceId').val();
                $('#cityId option:gt(0)').remove();//清空city选项卡，除第0个外
                $('#areaId option:gt(0)').remove();
                $.post(
                    '<%=request.getContextPath()%>/ajax3?method=selectCity',
                    {'provinceId' : provinceId},
                    function (jsonResult) {
                        console.log(jsonResult);
                        $(jsonResult.data).each(function () {
                            //<option value="3444">XX市</option>
                            $('#cityId').append('<option value="'+this.id+'">'+this.city+'</option>');
                        });
                    },
                    'json'
                );
            });

            $('#cityId').change(function () {
               var cityId = $('#cityId').val();
               $('#areaId option:gt(0)').remove();
               $.post(
                   '<%=request.getContextPath()%>/ajax3?method=selectArea',
                   {"cityId" : cityId},
                   function (jsonResult) {
                       console.log(jsonResult);
                       $(jsonResult.data).each(function () {
                           //<option value="433">XX区</option>
                           $('#areaId').append('<option value="'+this.id+'">'+this.area+'</option>');
                       });
                   },
                   'json'
               );
            });
        });
    </script>
</body>
</html>
