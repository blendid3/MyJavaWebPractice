<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 4/24/2021
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<script>
    function add(goodName) {
        document.getElementById(goodName).innerHTML =  parseInt(document.getElementById(goodName).textContent) + 1;
    }
    function decrease(goodName) {
        var value = parseInt(document.getElementById(goodName).textContent);
        if (value <= 0 ) {
            document.getElementById(goodName).innerHTML = "0";
        } else {
            document.getElementById(goodName).innerHTML =  value - 1;
        }
    }
    function clearValue(goodName) {
        document.getElementById(goodName).innerHTML = "0";
    }
    function submitForm() {
        <c:forEach var="good" items="${goods}">
        document.getElementById("${good.id}").value = document.getElementById("${good.NAME}").textContent;
        </c:forEach>
    }



</script>

<ul>

    <c:forEach var="good" items="${goods}">
        <h1>${good.NAME}</h1>
        <a href="controller?action=good_detail&goodName=${good.NAME}"><img src="./images/${good.image}" alt="product_image"></a>

        <br>
        <button type="button" onclick=add("${good.NAME}")>add</button>
        <button type="button" onclick=decrease("${good.NAME}")>decrease</button>
        <button type="button" onclick=clearValue("${good.NAME}")>clear</button>

        <li>click it to buy it!!! purchasing amount: <p id="${good.NAME}">0</p> </li>
    </c:forEach>
</ul>
<a href="mainPlatform.jsp">return back</a>
<form action="controller">
    <c:forEach var="good" items="${goods}">
        <input id="${good.id}" type="hidden" name="${good.id}">
    </c:forEach>
    <input type="hidden" name="action" value="submit">
    <input type="submit" onclick=submitForm()>
</form>
<%--<a href="controller?action=submitOrder&">submit the order</a>--%>
</body>
</html>
