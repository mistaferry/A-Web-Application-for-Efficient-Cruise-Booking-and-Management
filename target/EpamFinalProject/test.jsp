<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<c:if test="${empty sessionScope.allCruises}">
    <p>LIST IS EMPTY</p>
</c:if>
<table>
<%--    <tr>--%>
<%--        <th>Id</th>--%>
<%--        <th>Name</th>--%>
<%--        <th>Surname</th>--%>
<%--        <th>Role</th>--%>
<%--        <th>Blocked</th>--%>
<%--    </tr>--%>
    <c:forEach var ="cruise" items="${sessionScope.allCruises}">
        <tr>
            <td><c:out value = "${cruise.firstName}"/></td>
            <td><c:out value = "${cruise.surname}"/></td>
<%--            <td><c:out value = "${cruise.paid}"/></td>--%>
<%--            <td><c:out value = "${cruise.numberOfPorts}"/></td>--%>
<%--            <td><c:out value = "${cruise.startPort}"/></td>--%>
<%--            <td><c:out value = "${cruise.endPort}"/></td>--%>
        </tr>
    </c:forEach>
</table>

