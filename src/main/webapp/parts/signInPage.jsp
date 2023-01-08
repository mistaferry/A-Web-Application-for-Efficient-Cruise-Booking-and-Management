<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="nl" />
<fmt:setBundle basename="resources" />
<link rel="stylesheet" href="css/newStylr.css"/>

<div class="form">
    <form method="post" id="form-enter" action="controller?action=sign-in">
        <input type="hidden" name="action" value="sign-in">


        <div>
            <label for="login"><fmt:message key="login.label.username" /></label><br>
            <input type="text" id="login" name="login"
                   pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}"><br><br>
        </div>
        <div>
            <label for="password"><fmt:message key="login.label.password" /></label><br>
            <input type="password" id="password" name="password"
                   required minlength="2" maxlength="10">
        </div>
        <br><br>
        <div>
            <button id="form-button" type="submit">
            <fmt:message key="login.button.submit"/></button>
        </div>
    </form>



</div>
