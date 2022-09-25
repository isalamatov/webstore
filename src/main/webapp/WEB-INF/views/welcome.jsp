<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.System" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Welcome</title>
</head>
<hr>
<body>
<section>
    <div class="media">
        <img src="/images/Dasha_in_GUM.jpeg" class="mr-3" alt="...">
        <div class="media-body">
            <h5 class="mt-0">Как однажды Айро сказал Зуко:</h5>
            Если долго сидеть на берегу реки мимо тебя проплывет труп твоего поверженного врага.
        </div>
    </div>
    <div class="jumbotron">
        <h1 class="display-4"> ${greeting} </h1>
        <p class="navbar-left"> ${tagline} </p>
        <p><br></p>
        <p class="lead"> Time </p>
        <p class="lead">
            <a class="ui-icon-clock"> <%= LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy")) %> </a>
        </p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="/webstore/products/all"
               role="button"> Products
            </a>
        </p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="/webstore/customer"
               role="button"> Customers
            </a>
        </p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="/webstore/customer"
               role="button"> Customers
            </a>
        </p>
    </div>
</section>
</body>
</html>
