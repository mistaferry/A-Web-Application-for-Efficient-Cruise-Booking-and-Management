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

<form method="get" action="controller">
    <input type="hidden" name="action" value="admin-get-user-cruises">
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

<section class="table">
    <form action="controller" method="get">
        <input type="hidden" name="action" value="change-payment-value">
        <div>
            <table>
                <thead>
                <tr>
                    <td>Id</td>
                    <td>Ship Name</td>
                    <td>Start</td>
                    <td>Duration</td>
                    <td>Price</td>
                    <td>Paid</td>
                    <td class="no-style"></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cruise" items="${requestScope.userCruises}">
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
                                ${cruise.duration} days
                        </td>
                        <td>
                                ${cruise.price} UAH
                        </td>
                        <td>
                                ${cruise.paid}
                        </td>
                        <td class="max-width">
                            <input type="hidden" value="${cruise.id}" name="cruise_id">
                            <input class="button-order max-width white" type="submit" value="Change payment status"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</section>

<nav class="page-nav">
    <ul class="pagination">
        <c:if test="${param.page != 0}">
            <li><a class="page-link"
                   href="controller?action=view-user-cruises&page=${param.page-1}&cruisePerPage=${param.cruisePerPage}"
                   tabindex="-1">Previous</a></li>
        </c:if>
        <c:forEach var="num" begin="0" end="${requestScope.pageAmount}">
            <li class="page-item ${param.page == num ? "active" : ""}">
                <a class="page-link"
                   href="controller?action=view-user-cruises&page=${num}&cruisePerPage=${param.cruisePerPage}">
                        ${num+1}
                </a>
            </li>
        </c:forEach>

        <c:if test="${param.page lt requestScope.pageAmount}">
            <li>
                <a class="page-link"
                   href="controller?action=view-user-cruises&page=${param.page+1}&cruisePerPage=${param.cruisePerPage}">
                    Next
                </a>
            </li>
        </c:if>
    </ul>
</nav>

<%@ include file="/parts/footer.jspf" %>

</html>
