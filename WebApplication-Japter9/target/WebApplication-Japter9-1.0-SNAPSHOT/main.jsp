<%--
  Created by IntelliJ IDEA.
  User: zhqbl
  Date: 3/31/2021
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Shopping Mall</title>
    <link rel = "stylesheet" type="text/css" href="css/public.css">
    <style type="text/css">
        a:link {
            font-size: 18px;
            color: #DB8400;
            text-decoration: none;
            font-weight: bolder;
        }

        a:visited {
            font-size: 18px;
            color: #DB8400;
            text-decoration: none;
            font-weight: bolder;
        }

        a:hover {
            font-size: 18px;
            color: #DB8400;
            text-decoration: underline;
            font-weight: bolder;
        }
    </style>
</head>
<body>
<div class="header">智捷网上电脑商城</div>
<hr width="100%"/>
<div>
    <p class="text1"><img src="images/4.jpg" align="absmiddle"/> <a href="controller?action=list">shopping list</a></p>
    <p class="text2"> you can buy your interested product from shopping list </p>
</div>
<hr width="100%"/>
<div>
    <p class="text1"><img src="images/mycar1.jpg" align="absmiddle"/> <a href="controller?action=cart">shopping cart</a></p>
    <p class="text2"> you could put your interesting product to your cart </p>
</div>
<div class="footer">
    <hr width="100%"/>
    Copyright © shopping Mall 2008-2018. All Rights Reserved
</div>
</body>
</html>
