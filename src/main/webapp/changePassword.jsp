<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="nl"/>
<fmt:setBundle basename="resources"/>
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

<form method="post" action="controller">
    <input type="hidden" name="action" value="change-password">
    <div class="info">
        <div>
            <h1>Change Password</h1>
        </div>
        <div>
            <h4>Old password</h4>
            <label>
                <input type="password" name="oldPassword"/>
            </label>
            <h4>New password</h4>
            <label>
                <input type="password" name="newPassword"/>
            </label>
            <h4>Confirm password</h4>
            <label>
                <input type="password" name="confirmPassword"/>
            </label>
        </div>
        <br>
        <button id="form-button" type="submit">Save changes</button>
    </div>
</form>

<jsp:include page="/parts/footer.jsp"/>
</html>