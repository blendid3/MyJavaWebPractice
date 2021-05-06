<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 5/1/2021
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Good Details Pages</title>
</head>
<body>
<%--<c:forEach var="" items="">--%>
<%--    --%>
<%--</c:forEach>--%>
good id: ${good.id} <br />
good name: ${good.NAME} <br />
good price: ${good.price} <br />
good brand: ${good.brand} <br />
good cpu_brand: ${good.cpu_brand} <br />
good cput_type: ${good.cpu_type} <br />
good memory_capacity: ${good.memory_capacity} <br />
good hd_capacity: ${good.hd_capacity} <br />
good card_model: ${good.card_model} <br />
good displaysize: ${good.displaysize} <br />
good image: <img src="images/${good.image}" alt=""> <br />



</body>
</html>
