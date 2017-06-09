<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/8
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (session.getAttribute("nick") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<h1>第二页。。</h1>
<p><%=session.getAttribute("nick")%>
</p>
<a href="home.jsp">回到主页</a>
</body>
</html>
