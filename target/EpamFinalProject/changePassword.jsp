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
        <%@ include file="/parts/css/newStylr.css"%>
    </style>
</head>

<%@ include file="parts/startMenu.jspf" %>

<form method="post" action="controller">
    <input type="hidden" name="action" value="change-password">
    <div class="info">
        <div>
            <h1><fmt:message key="change.password"/></h1>
        </div>
        <div>
            <h4><fmt:message key="person.login"/></h4>
            <label>
                <input type="text" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}" name="login"/>
            </label>
            <h4><fmt:message key="old.password"/></h4>
            <label>
                <input type="password" name="oldPassword" minlength="2" maxlength="10"/>
            </label>
            <h4><fmt:message key="new.password"/></h4>
            <label>
                <input type="password" name="newPassword" minlength="2" maxlength="10"/>
            </label>
            <h4><fmt:message key="confirm.password"/></h4>
            <label>
                <input type="password" name="confirmPassword" minlength="2" maxlength="10"/>
            </label>
            <c:if test="${sessionScope.error ne null}">
                <span class="error-text">${sessionScope.error}</span>
            </c:if>
            <%session.removeAttribute("error"); %>
        </div>
        <br>
        <button id="form-button" type="submit"><fmt:message key="button.save.changes"/></button>
<%--        <div class="login-option">--%>
<%--            <a href="index.jsp" class="login-option">I remember my password</a>--%>
<%--        </div>--%>
    </div>
</form>

<%@ include file="/parts/footer.jspf" %>

</html>