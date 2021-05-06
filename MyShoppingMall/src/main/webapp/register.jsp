<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 4/18/2021
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="error" items="${errors}">
        <li>${error}</li>
    </c:forEach>
</ul>
<form action="controller" method="post">
    <input type="text" placeholder="enter id" name="id">
    <input type="text" placeholder="enter name" name="name">
    <input type="text" placeholder="enter password" name="password">
    <input type="text" placeholder="enter password again" name="password2">
    <input type="text" placeholder="enter address" name="address">
    <input type="text" placeholder="enter phone" name="phone">
    <input type="text" placeholder="enter birthday" name="birthday">
    <input type="submit" placeholder="Submit">
    <input type="hidden" name = "action" value="reg">
</form>
<a href="login.jsp">Already have accounts? Login here</a>
</body>
</html>
