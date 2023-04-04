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
                    <img src="<c:url value="/resource/images/welcome_1.png"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
                <div class="col-sm-4">
                    <img src="<c:url value="/resource/images/welcome_2.png"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
                <div class="col-sm-4">
                    <img src="<c:url value="/resource/images/welcome_3.png"></c:url>" class="mr-3" alt="image"
                         style="width: 100%">
                </div>
            </div>
            <div class="media-body">
                <h5 class="mt-0">The only way to buy something is to sell something</h5>
                Folk wisdom.
            </div>
            <p class="lead">
                <a class="ui-icon-clock"> </a>
            </p>
            <p class="lead">
        </div>
    </div>
</section>
</body>
</html>

