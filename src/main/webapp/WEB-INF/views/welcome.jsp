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
        <img src="${pageContext.request.contextPath}/resources/Dasha_in_GUM.jpeg)" class="mr-3" alt="...">
        <div class="media-body">
            <h5 class="mt-0">Media heading</h5>
            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
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
