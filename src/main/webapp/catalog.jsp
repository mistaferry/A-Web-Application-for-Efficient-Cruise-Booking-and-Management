<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<jsp:include page="/parts/cruiseView.jsp"/>

<jsp:include page="/parts/footer.jsp"/>
</html>
