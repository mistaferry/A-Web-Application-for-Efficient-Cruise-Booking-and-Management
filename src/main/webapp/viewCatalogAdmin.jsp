<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
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
        <%@ include file="parts/css/newStylr.css"%>
    </style>
</head>

<%@ include file="parts/userMenuChoose.jspf" %>

<form method="get" action="controller">
    <input type="hidden" name="action" value="admin-view-all-cruises">
    <div>
        <label>
            <select name="page" style="display: none">
                <option value="0" selected></option>
            </select>
        </label>
        <label>
            <select name="cruisePerPage" style="display: none">
                <option value="7" selected></option>
            </select>
        </label>
    </div>
</form>

<%--<div class="button-add-new">--%>
<%--    <a href="controller?action=view-existing-data">Add new cruise</a>--%>
<%--</div>--%>

<section class="table">
    <div>
        <table>
            <thead>
            <tr>
                <td><fmt:message key="label.id"/></td>
                <td><fmt:message key="label.ship.name"/></td>
                <td><fmt:message key="label.startDay"/></td>
                <td><fmt:message key="label.duration"/></td>
                <td><fmt:message key="label.price"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cruise" items="${sessionScope.allCruises}">
                <tr>
                    <td>
                            ${cruise.id}
                    </td>
                    <td>
                            ${cruise.ship.name}
                    </td>
                    <td>
                            ${cruise.startDate.toLocalDate().dayOfMonth} ${cruise.startDate.toLocalDate().month} ${cruise.startDate.toLocalDate().year}
                    </td>
                    <td>
                            ${cruise.duration} <fmt:message key="label.days"/>
                    </td>
                    <td>
                            ${cruise.price} <fmt:message key="label.uah"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<nav class="page-nav">
    <ul class="pagination">
        <c:if test="${param.page != 0}">
            <li><a class="page-link"
                   href="controller?action=admin-view-all-cruises&page=${param.page-1}&cruisePerPage=${param.cruisePerPage}"
                   tabindex="-1"><fmt:message key="pagination.previous"/></a></li>
        </c:if>
        <c:forEach var="num" begin="0" end="${requestScope.pageAmount}">
            <li class="page-item ${param.page == num ? "active" : ""}">
                <a class="page-link"
                   href="controller?action=admin-view-all-cruises&page=${num}&cruisePerPage=${param.cruisePerPage}">
                        ${num+1}
                </a>
            </li>
        </c:forEach>

        <c:if test="${param.page lt requestScope.pageAmount}">
            <li>
                <a class="page-link"
                   href="controller?action=admin-view-all-cruises&page=${param.page+1}&cruisePerPage=${param.cruisePerPage}">
                    <fmt:message key="pagination.next"/>
                </a>
            </li>
        </c:if>
    </ul>
</nav>

<%@ include file="/parts/footer.jspf" %>

</html>