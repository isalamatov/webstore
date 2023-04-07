<%--
  Created by IntelliJ IDEA.
  User: isalamatov
  Date: 24.10.2022
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>Invalid promocode</title>
</head>
<section>
  <div class="container">
    <p>
      <a href="<spring:url value="/products" />"
         class="btn btn-primary">
        <span class="glyphicon-hand-left glyphicon"></span>
        products
      </a>
    </p>
  </div>
</section>
</html>
