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

<div class="info">
    <div>
        <h1><fmt:message key="user.profile"/></h1>
    </div>
    <div>
        <h4><fmt:message key="person.name"/></h4>
        <p>${sessionScope.user.firstName}</p>
        <h4><fmt:message key="person.surname"/></h4>
        <p>${sessionScope.user.surname}</p>
        <h4><fmt:message key="person.login"/></h4>
        <p>${sessionScope.user.login}</p>
    </div>
    <div class="action-button">
        <a href="editProfile.jsp"><fmt:message key="edit.profile"/></a>
    </div>
    <br>
    <div class="action-button">
        <a href="changePassword.jsp"><fmt:message key="change.password"/></a>
    </div>
</div>

<%@ include file="/parts/footer.jspf" %>

</html>