<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="nl" />
<fmt:setBundle basename="resources" />
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="/parts/css/newStylr.css"%>
</style>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title>
        Cruise Company
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<jsp:include page="/parts/mainMenu.jsp"/>

<div class="info">
    <div>
        <h1>User Profile</h1>
    </div>
    <div>
        <h4>Name</h4>
        <p>${sessionScope.user.firstName}</p>
        <h4>Surname</h4>
        <p>${sessionScope.user.surname}</p>
        <h4>Login</h4>
        <p>${sessionScope.user.login}</p>
    </div>
    <div class="action-button">
        <a href="editProfile.jsp">Edit profile</a>
    </div>
    <br>
    <div class="action-button">
        <a href="changePassword.jsp">Change password</a>
    </div>
</div>

<jsp:include page="/parts/footer.jsp"/>
</html>