<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="en" />--%>
<%--<%@ page isELIgnored="false" %>--%>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>


<!DOCTYPE html>
<html lang="${param.lang}">
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
        <%@ include file="parts/languagePage.jspf" %>

        <%@ include file="parts/singInPage.jspf" %>

        <%@ include file="parts/singInPage.jspf" %>
    </body>
</html>
