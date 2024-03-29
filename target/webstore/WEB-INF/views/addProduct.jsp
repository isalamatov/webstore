<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Add product</title>
</head>

<section class="container">
    <meta charset="UTF-8">
    <form:form modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend>Add new product</legend>
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="productId"><spring:message
                        code="addProduct.form.productId.label"/></label>
                <div class="col-lg-10">
                    <form:input id="productId" path="productId" type="text" class="form:input-large"/>
                    <form:errors path="productId" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="name"><spring:message
                        code="addProduct.form.name.label"/></label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form:input-large"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitPrice"><spring:message
                        code="addProduct.form.unitPrice.label"/></label>
                <div class="col-lg-10">
                    <div class="form:input-prepend">
                        <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                        <form:errors path="unitPrice" cssClass="text-danger"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="description"><spring:message
                        code="addProduct.form.description.label"/></label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="description" rows="2"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="manufacturer"><spring:message
                        code="addProduct.form.manufacturer.label"/></label>
                <div class="col-lg-10">
                    <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
                    <form:errors path="manufacturer" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="category"><spring:message
                        code="addProduct.form.category.label"/></label>
                <div class="col-lg-10">
                    <form:input id="category" path="category" type="text" class="form:input-large"/>
                    <form:errors path="category" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock"><spring:message
                        code="addProduct.form.unitsInStock.label"/></label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
                    <form:errors path="unitsInStock" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                    <%--@declare id="condition"--%><label class="control-label col-lg-2" for="condition"><spring:message
                    code="addProduct.form.condition.label"/></label>
                <div class="col-lg-10">
                    <form:radiobutton path="condition" value="New"/>New
                    <form:radiobutton path="condition" value="Old"/>Old
                    <form:radiobutton path="condition" value="Refurbished"/>Refurbished
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="productImage"> <spring:message
                        code="addProduct.form.productImage.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productImage" path="productImage" type="file"
                                class="form:input-large"/>
                    <form:errors path="productImage" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="productManual"> <spring:message
                        code="addProduct.form.productManual.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productManual" path="productManual" type="file"
                                class="form:input-large"/>
                    <form:errors path="productManual" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
                </div>
            </div>
                <%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
        </fieldset>
    </form:form>
</section>
</html>
