<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.System" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Welcome to webstore</title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script> var currentTime = "${timeLine}" </script>
    <script>
        function timeRenew() {
            var time = new Date();
            $('.ui-icon-clock').html(time);
        }

        $(document).ready(function () {
            setInterval(timeRenew, 1000)
        });
    </script>
</head>
<body>
<section>
    <div class="media">
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <img src="<c:url value="/resource/images/gum.jpeg"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
                <div class="col-sm-4">
                    <img src="<c:url value="/resource/images/grass.jpeg"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
                <div class="col-sm-4">
                    <img src="<c:url value="/resource/images/gum.jpeg"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
            </div>
            <div class="media-body">
                <h5 class="mt-0">Once Airo told Zuko:</h5>
                If you'd sit on the riverside for enough time you'd see your enemy's body, flowing down the river.
            </div>
            <p class="lead">
                <a class="ui-icon-clock"> </a>
            </p>
            <p class="lead">
        </div>
        <div class="jumbotron">
            <h1 class="display-4"> ${greeting} </h1>
            <p class="navbar-left"> ${tagline} </p>
            <br>
            <div class="d-grid gap-2">
                <a class="btn btn-primary btn-lg" href="/webstore/products/"
                   role="button"> Products
                </a>
                <a class="btn btn-primary btn-lg" href="/webstore/customer"
                   role="button"> Customers
                </a>
                <a class="btn btn-primary btn-lg" href="/webstore/products/add"
                   role="button"> Add new product
                </a>
            </div>
        </div>
</section>
</body>
</html>
