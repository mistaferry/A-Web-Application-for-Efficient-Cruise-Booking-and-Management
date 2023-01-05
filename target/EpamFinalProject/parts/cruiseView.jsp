<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<img src="src/main/webapp/images/cruise1.jpg"
     class="cruise_image"/>

<c:if test="${empty sessionScope.cruises}">
    <p>LIST IS EMPTY</p>
</c:if>
<table>
    <tr>
        <%--        <th>Id</th>--%>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>Blocked</th>
    </tr>
    <c:forEach var = "cruise" items="${sessionScope.cruises}">
        <tr>
            <td><c:out value = "${cruise.firstName}"/></td>
            <td><c:out value = "${cruise.surname}"/></td>
            <td><c:out value = "${cruise.roleId}"/></td>
            <td><c:out value = "${cruise.blocked}"/></td>
        </tr>
    </c:forEach>
</table>

