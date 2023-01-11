<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en" />
<fmt:setBundle basename="resources" />
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="/parts/css/newStylr.css"%>
</style>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title>
        <fmt:message key="company.name"/>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<jsp:include page="/parts/customerMenu.jsp"/>

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

<jsp:include page="/parts/footer.jspf"/>

</html>