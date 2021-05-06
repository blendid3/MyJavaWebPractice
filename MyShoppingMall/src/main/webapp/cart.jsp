<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 4/25/2021
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Product list:</h1>
<c:forEach var="product" items="${productList}" >
    <ul>
        <li>${product}: ${cart.get(product)}</li>
    </ul>
</c:forEach>
total price: ${total_price}
<a href="controller?action=clear_cart">clear cart</a>
<a href="mainPlatform.jsp">return to Home</a>
<%--${productList.get(0)} : ${cart.get(productList.get(0))}--%>
</body>
</html>
