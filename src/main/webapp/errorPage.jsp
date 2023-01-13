<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
    <title><fmt:message key="company.name"/></title>
</head>
<body>
<h1>ERROR!!!!!!!!</h1>
</body>
</html>
