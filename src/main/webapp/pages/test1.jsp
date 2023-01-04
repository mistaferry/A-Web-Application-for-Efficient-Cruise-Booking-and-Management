<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title>
        Cruise Company
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<jsp:include page="/parts/mainMenu.jsp"/>

<jsp:include page="test.jsp"/>

<jsp:include page="/parts/footer.jsp"/>
</html>
