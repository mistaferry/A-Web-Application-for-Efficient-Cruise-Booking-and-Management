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

<c:choose>
    <c:when test="${empty requestScope.userCruises}">
        <section class="table">
            <table>
                <tr>
                    <td>
                        <h2>All cruises are paid</h2>
                    </td>
                </tr>
            </table>
        </section>
    </c:when>
    <c:otherwise>
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
                        <td><fmt:message key="label.booking.date"/></td>
                        <td><fmt:message key="label.payment.status"/></td>
                        <td class="no-style"></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${requestScope.userCruises}">
                        <tr>
                            <td>
                                <input type="hidden" name="order_cruise_id" value="${order.cruise.id}">
                                ${order.cruise.id}
                            </td>
                            <td>
                                    ${order.cruise.ship.name}
                            </td>
                            <td>
                                    ${order.cruise.startDate.toLocalDate().dayOfMonth} ${order.cruise.startDate.toLocalDate().month} ${order.cruise.startDate.toLocalDate().year}
                            </td>
                            <td>
                                    ${order.cruise.duration} <fmt:message key="label.days"/>
                            </td>
                            <td>
                                    ${order.cruise.price} <fmt:message key="label.uah"/>
                            </td>
                            <td>
                                    ${order.dateOfRegistration.toLocalDate().dayOfMonth} ${order.dateOfRegistration.toLocalDate().month} ${order.dateOfRegistration.toLocalDate().year}
                            </td>
                            <c:choose>
                                <c:when test="${order.paid}">
                                    <td style="background: lightgreen">
                                        <fmt:message key="label.status.paid"/>
                                    </td>
                                </c:when>
                                <c:when test="${!order.paid}">
                                    <td style="background: lightcoral">
                                        <fmt:message key="label.status.unpaid"/>
                                    </td>
                                </c:when>
                            </c:choose>
                            <td class="max-width">
                                <form action="controller" method="get">
                                    <input type="hidden" name="action" value="change-payment-value">
                                    <input type="hidden" value="${order.cruise.id}" name="order_cruise_id">
                                    <input type="hidden" value="${order.dateOfRegistration}" name="date">
                                    <input class="button-order max-width white" type="submit" value="Change payment status"/>
                                </form>
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
                           href="controller?action=view-user-cruises&page=${param.page-1}&cruisePerPage=${param.cruisePerPage}"
                           tabindex="-1"><fmt:message key="pagination.previous"/></a></li>
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
                            <fmt:message key="pagination.next"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>

<%@ include file="/parts/footer.jspf" %>

</html>
