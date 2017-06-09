<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String nick = request.getParameter("nick");
    out.print(nick);
    String mobile = request.getParameter("mobile");
    out.print(mobile);
    String password = request.getParameter("password");
    out.print(password);

    String[] hobbies = request.getParameterValues("hobbies");
    String[] cities = request.getParameterValues("cities");

    System.out.println("hobbies: " + Arrays.toString(hobbies));
    System.out.println("cities: " + Arrays.toString(cities));

    new Driver();
    Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");

    String sqlNick = "SELECT * FROM db_javaee.user WHERE nick=?";
    PreparedStatement statement = connection.prepareStatement(sqlNick);
    statement.setString(1, nick);
    ResultSet resultSet = statement.executeQuery();
    boolean isNickExist = resultSet.next();
    String sqlMobile = "SELECT * FROM db_javaee.user WHERE mobile=?";
    statement = connection.prepareStatement(sqlMobile);
    statement.setString(1, mobile);
    resultSet = statement.executeQuery();
    boolean isMobileExist = resultSet.next();

    if (isNickExist) {
        request.setAttribute("message", "昵称已经存在");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else if (isMobileExist) {
        request.setAttribute("message", "手机号已经存在");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else {
        String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nick);
        statement.setString(2, mobile);
        statement.setString(3, password);
        statement.executeUpdate();
        response.sendRedirect("index.jsp");
    }
    resultSet.close();
    statement.close();
    connection.close();
%>
</body>
</html>
