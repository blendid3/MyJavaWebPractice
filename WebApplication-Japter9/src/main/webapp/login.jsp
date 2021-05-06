<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 3/26/2021
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="css/LoginStyle.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Sign in</title>
</head>

<body>
<div class="header">PC Shopping Mall</div>
<hr width="100%"/>
<%--显示错误消息--%>
<ul>
    <c:forEach var="error" items="${errors}">
        <li class="error">${error}</li>
    </c:forEach>
</ul>

<div class="main">
    <p class="sign" align="center">Sign in</p>
    <form class="form1" action="controller" method="post">
        <input class="un " type="text" align="center" placeholder="Username" name="userid">
        <input class="pass" type="password" align="center" placeholder="Password" name="password">
        <input type="submit" value="Sign in" onclick="document.forms[0].fn.value='login'">
<%--        <a class="submit" align="center" onclick="document.forms[0].fn.value='login'">Sign in</a>--%>
        <p class="forgot" align="center"><a href="#">Forgot Password? </a></p>
        <input type="hidden" name="action" value="login">
    </form>

</div>


<%@include file="footer.jsp" %>
</body>
</html>
