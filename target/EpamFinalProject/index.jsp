<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
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
<body>

<%@ include file="parts/signInMenu.jspf" %>

<%@ include file="parts/singInPage.jspf" %>

<%@ include file="parts/footer.jspf" %>

</body>
</html>
