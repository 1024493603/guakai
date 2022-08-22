<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/28
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--1、往域对象中放数据--%>
    <%
        pageContext.setAttribute("age", 18);
        pageContext.setAttribute("gender", "男");
    %>
    <c:set var="age" value="23" scope="page"></c:set>
    ${age}<br/>

    <hr/>
    <%--2、条件判断--%>
    <c:if test="${gender=='男'}">
        男
    </c:if>
    <c:if test="${gender=='女'}">
        女
    </c:if>

    <hr/>
    <%--3、多条件判断--%>
    <c:set var="score" value="67"></c:set>
    <c:choose>
        <c:when test="${score >= 90 && score <= 100}">
            优秀
        </c:when>
        <c:when test="${score >= 80 && score < 90}">
            良好
        </c:when>
        <c:when test="${score >= 70 && score < 80}">
            一般
        </c:when>
        <c:when test="${score >= 60 && score < 80}">
            及格
        </c:when>
        <c:otherwise>
            不及格
        </c:otherwise>
    </c:choose>

    <hr/>
    <%--4、集合遍历
        4.1、遍历List<Student>
        4.2、遍历Map<String, String>
             遍历Map<String, Student>
    --%>
    <%-- 默认：step=1 类似于i++ --%>
    <c:forEach begin="1" end="10" var="i" step="2">
        ${i}
    </c:forEach>
    <br/>
   <%-- 4.1、遍历List<Student>--%>
    <%
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(1, "zhansgan1", 23, "男");
        Student student2 = new Student(2, "zhansgan2", 23, "男");
        Student student3 = new Student(3, "zhansgan3", 23, "男");
        Student student4 = new Student(4, "zhansgan4", 23, "男");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        application.setAttribute("list", list);
    %>
    <c:forEach items="${list}" var="student">
        ${student.name}--${student.age}--${student.gender}<br/>
    </c:forEach>

    <hr/>
    <%--4.2、遍历Map<String, String>--%>
    <%
        Map<String, String> map = new HashMap<>();
        map.put("cn", "中国");
        map.put("us", "美国");
        request.setAttribute("map", map);
    %>
    <c:forEach items="${map}" var="entry">
        ${entry.key}--${entry.value}<br/>
    </c:forEach>

    <hr/>
    <%--遍历Map<String, Student>--%>
    <%
        Map<String, Student> stuMap = new HashMap<>();
        stuMap.put("studen1", student1);
        stuMap.put("studen2", student2);
        stuMap.put("studen3", student3);
        request.setAttribute("stuMap", stuMap);
    %>
    <c:forEach items="${stuMap}" var="entry">
        ${entry.key}--${entry.value}--${entry.value.name}<br/>
    </c:forEach>
</body>
</html>
