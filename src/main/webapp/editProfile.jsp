<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<form method="post" action="controller">
    <input type="hidden" name="action" value="edit-user-profile">
    <div class="info">
        <div>
            <h1><fmt:message key="edit.profile"/></h1>
        </div>
        <div>
            <c:set var="userName" value="${sessionScope.prevUser.firstName eq null ? sessionScope.user.firstName : sessionScope.prevUser.firstName}"/>
            <c:set var="userSurname" value="${sessionScope.prevUser.surname eq null ? sessionScope.user.surname : sessionScope.prevUser.surname}"/>
            <c:set var="userLogin" value="${sessionScope.prevUser.login eq null ? sessionScope.user.login : sessionScope.prevUser.login}"/>
            <h4><fmt:message key="person.name"/></h4>
            <label>
                <input type="text" name="firstName" value="${userName}"/>
            </label>
            <h4><fmt:message key="person.surname"/></h4>
            <label>
                <input type="text" name="surname" value="${userSurname}"/>
            </label>
            <h4><fmt:message key="person.login"/></h4>
            <label>
                <input type="text" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}" name="login" value="${userLogin}"/>
            </label>
        </div>
        <br>
        <button id="form-button" type="submit"><fmt:message key="button.save.changes"/></button>
    </div>
</form>

<jsp:include page="/parts/footer.jsp"/>

</html>