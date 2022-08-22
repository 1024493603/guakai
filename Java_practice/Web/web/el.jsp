<%@ page import="com.situ.web.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Gao
  Date: 2022/7/28
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        // 内置对象 response
        // pageContext、request、session、application(ServletContext)
        // ServletContext application = new ServletContext();
        // pageContext.setAttribute();
        // 1、普通字符串
        pageContext.setAttribute("name", "zhansgan");
        request.setAttribute("name", "lisi");
        // 2、Student对象
        Student student = new Student(1, "zhansgan", 23, "男");
        session.setAttribute("student", student);
        // 3、List<Student>
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
    <%--1、获取普通字符串--%>
    <%--JSP表达式--%>
    <%=pageContext.getAttribute("name")%><br/>
    <%=request.getAttribute("name")%><br/>
    <%--先去范围小的域对象找，找不到再去范围大的域对象找--%>
    <%=pageContext.findAttribute("name")%><br/>
    <%--EL表达式--%>
    ${pageScope.name}<br/>
    ${requestScope.name}<br/>
    ${name}<br/>

    <hr/>
    <%--2、获取Student对象--%>
    <%--JSP表达式--%>
    <%
        Student stu = (Student) session.getAttribute("student");
    %>
    <%=stu.getName()%><br/>
    <%=stu.getAge()%><br/>
    <%--EL表达式  getName()--%>
    ${student.name}<br/>
    ${student.age}<br/>

    <hr/>
    <%--3、List<Student>--%>
    <%--JSP表达式--%>
    <%
        List<Student> stuList = (List<Student>) application.getAttribute("list");
    %>
    <%=stuList.get(0).getName()%><br/>
    <%--EL表达式--%>
    ${applicationScope.stuList}<br/>
    ${stuList[0].name}<br/>
</body>
</html>
