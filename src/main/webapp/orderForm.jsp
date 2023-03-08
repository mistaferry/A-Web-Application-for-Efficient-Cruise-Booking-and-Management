<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>
        <fmt:message key="company.name"/>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@ include file="parts/css/newStylr.css"%>
    </style>
</head>

<%@ include file="parts/userMenuChoose.jspf" %>

<form class="form-reg" action="controller" enctype="multipart/form-data" method="post">
    <div>
        <h1>Placing an order</h1>
    </div>
    <div class="flex">
        <input type="hidden" name="action" value="place-order">
        <div class="form-left-right">
            <label><fmt:message key="label.person.name"/><br>
                <input class="input1" type="text" value="${sessionScope.user.firstName}" readonly/>
            </label><br><br>
            <label><fmt:message key="label.person.surname"/><br>
                <input class="input1" type="text" value="${sessionScope.user.surname}" readonly/>
            </label><br><br>
            <label><fmt:message key="login.label.username"/><br>
                <input class="input1" type="email" value="${sessionScope.user.login}" readonly>
            </label>
            <br><br>
            <label><fmt:message key="label.documents.upload"/><br>
                <input class="input1" type="file" name="copyDocument">
            </label>
        </div>
        <div class="button">
            <input type="hidden" name="cruise_id" value="${requestScope.orderCruise.id}">
            <input class="button-order max-width" type="submit" value="<fmt:message key="button.submit"/>">
        </div>
    </div>

</form>

<%@ include file="/parts/footer.jspf" %>

</html>