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
        <%@ include file="/parts/css/newStylr.css"%>
    </style>
</head>

<%@ include file="parts/userMenuChoose.jspf" %>

<form method="get" action="controller">
    <input type="hidden" name="action" value="change-user-values-by-admin">
    <div class="info">
        <div>
            <h1><fmt:message key="user.profile"/></h1>
        </div>
        <div>
            <h4><fmt:message key="label.person.name"/></h4>
            <p>${sessionScope.chosenUser.firstName}</p>
            <h4><fmt:message key="label.person.surname"/></h4>
            <p>${sessionScope.chosenUser.surname}</p>
            <h4><fmt:message key="label.person.login"/></h4>
            <p>${sessionScope.chosenUser.login}</p>
            <h4><fmt:message key="label.person.role"/></h4>
            <label>
                <select name="role" class="select">
                    <option value="2" ${sessionScope.chosenUser.roleId == "2" ? "selected" : ""}>ADMIN</option>
                    <option value="1" ${sessionScope.chosenUser.roleId == "1" ? "selected" : ""}>CUSTOMER</option>
                </select>
            </label>
            <h4><fmt:message key="label.person.is.blocked"/></h4>
            <label>
                <select name="account_status" class="select">
                    <option value="true" ${sessionScope.chosenUser.blocked eq true ? "selected" : ""}>BLOCKED</option>
                    <option value="false" ${sessionScope.chosenUser.blocked eq false ? "selected" : ""}>UNBLOCKED</option>
                </select>
            </label>
            <div>
                <br>
                <button id="form-button" type="submit"><fmt:message key="button.save.changes"/>
            </div>
        </div>
        <br>
    </div>
</form>

<%@ include file="/parts/footer.jspf" %>

</html>