<%--
  Created by IntelliJ IDEA.
  User: isalamatov
  Date: 21.09.2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Products</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Customers</h1>
            <p>All customers</p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/webstore"
                   role="button"> Home
                </a>
            </p>
        </div>
    </div>
</section>
<section class="container">
    <div class="row">
        <c:forEach items="${customers}" var="customer">
            <div class="col-sm-6 col-md-3" style="padding-bottom:15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>Customer</h3>
                        <h3>${customer.name}</h3>
                        <p>${customer.customerId}</p>
                        <p>${customer.address}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>
