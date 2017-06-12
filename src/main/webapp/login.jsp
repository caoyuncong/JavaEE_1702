<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String mobile = request.getParameter("mobile");
    String password = request.getParameter("password");

    new Driver();
    Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
    String sql = "SELECT * FROM db_javaee.user WHERE mobile=?AND password=?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, mobile);
    statement.setString(2, password);
    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
        // success
//        response.sendRedirect("home.jsp"); // 不能保存request 范围内的属性,可以保存session范围内属性
        session.setAttribute("nick",resultSet.getString("nick"));
        request.getRequestDispatcher("home.jsp").forward(request,response); // 可以保存
    } else {
        // failed
//        response.sendRedirect("index.jsp"); // redirect 重定向 地址栏地址有变化
        request.setAttribute("message","用户名或密码错误");
        request.getRequestDispatcher("index.jsp").forward(request, response); // forward 转发 地址栏地址没有变化
    }

    resultSet.close();
    statement.close();
    connection.close();
%>
</body>
</html>
