<%@ page import="com.practice.web.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 10244
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
            //内置对象 response pageContext, request, session, application(ServletContext)
            //1.普通字符串
            pageContext.setAttribute("name","张三");
            request.setAttribute("name","李四");

            //2.创建student对象
            Student student = new Student(1,"哈" ,2,"pa");
            session.setAttribute("student", student);

            // 3、List<Student>
            List<Student> list = new ArrayList<>();
            Student student1 = new Student(1, "张三1", 23, "男");
            Student student2 = new Student(2, "张三2", 23, "男");
            Student student3 = new Student(3, "张三3", 23, "男");
            Student student4 = new Student(4, "张三4", 23, "男");
            list.add(student1);
            list.add(student2);
            list.add(student3);
            list.add(student4);
            application.setAttribute("list", list);
        %>
    <%--    1.普通获取字符串--%>
    <%--    JSP表达式--%>
        <%=pageContext.getAttribute("name")%><br/>
        <%=request.getAttribute("name")%><br/>
    <%--    先从小范围的域对象找，找不到去范围大的地方找--%>
        <%=pageContext.findAttribute("name")%><br/>
    <%--    EL表达式--%>
        ${pageScope.name}<br/>
        ${requestScope.name}<br/>
    <%--    先小范围，后大范围--%>
        ${name}<br/>

        <hr/>

    <%--    2.获取student对象--%>
    <%--    JSP表达式--%>
        <%
            Student stu = (Student) session.getAttribute("student");
        %>
        <%=stu.getName()%><br/>
        <%=stu.getAge()%><br/>
    <%--    EL表达式 默认调用get方法--%>
        ${student.name}<br/>
        ${student.age}<br/>
        <hr/>
    <%--    3.List<Student>--%>
    <%--    JSP方法--%>
        <%
            List<Student> stuList = (List<Student>) application.getAttribute("list");
        %>
        <%=stuList.get(0).getName()%>
        <%--EL表达式--%>
        ${applicationScope.stuList}<br/>
        ${stuList[0].name}<br/>
    </body>
</html>
