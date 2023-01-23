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

<section>
    <div>
        <label>
            <select name="selectShip">
                <c:forEach var="ship" items="${sessionScope.ships}">
                    <option value="${ship.id}" ${param.cruisePerPage == "2" ? "selected" : ""}
                </c:forEach>
            </select>
        </label>
    </div>
</section>

<%@ include file="/parts/footer.jspf" %>

</html>
