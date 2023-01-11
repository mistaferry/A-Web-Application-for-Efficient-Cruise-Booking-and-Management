<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<style>
    <%@ include file="/parts/css/newStylr.css"%>
</style>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>
        <fmt:message key="company.name"/>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<jsp:include page="/parts/customerMenu.jspf"/>

<form method="post" action="controller">
    <input type="hidden" name="action" value="change-password">
    <div class="info">
        <div>
            <h1><fmt:message key="change.password"/></h1>
        </div>
        <div>
            <h4><fmt:message key="old.password"/></h4>
            <label>
                <input type="password" name="oldPassword"/>
            </label>
            <h4><fmt:message key="new.password"/></h4>
            <label>
                <input type="password" name="newPassword"/>
            </label>
            <h4><fmt:message key="confirm.password"/></h4>
            <label>
                <input type="password" name="confirmPassword"/>
            </label>
        </div>
        <br>
        <button id="form-button" type="submit"><fmt:message key="button.save.changes"/></button>
    </div>
</form>

<jsp:include page="/parts/footer.jspf"/>

</html>