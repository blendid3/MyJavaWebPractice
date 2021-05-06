<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 4/17/2021
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form action = "controller?action=login" method="post">
    <input type="text" placeholder="id" name="id">
    <input type="text" placeholder="password" name="password">
    <input type="submit" value="Submit">
<%--    <input type="hidden" name="action" value="login">--%>
</form>
<a href="register.jsp">register</a>
</body>
</html>
