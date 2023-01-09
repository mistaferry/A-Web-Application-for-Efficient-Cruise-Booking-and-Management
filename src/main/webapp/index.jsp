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
</head>

<div>
    <nav class="nav-enter sign-nav">
        <a id="company-name">Cruise Company</a>
        <a>
            <ul>
                <li><a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a></li>
                <li><a href="?sessionLocale=nl"><fmt:message key="label.lang.nl"/></a></li>
            </ul>

            <c:if test="${not empty param.sessionLocale}">
                <fmt:message key="language.changed"/>
                <button><a href="profile.jsp"><fmt:message key="button.save.changes"/></a></button>
            </c:if>
        </a>
    </nav>
</div>


<%--    <jsp:include page="parts/signInMenu.jsp"/>--%>

<jsp:include page="parts/signInPage.jsp"/>
<%--    <jsp:include page="parts/cruiseView.jsp"/>--%>

<jsp:include page="parts/footer.jsp"/>
</html>
