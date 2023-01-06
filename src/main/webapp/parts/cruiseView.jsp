<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="css/style.css"%>
</style>

<%--<nav class="c_header">--%>
<%--    <p class="c_category_name">${empty param.category || param.category == 0 ? "All dishes" : categories.get(param.category-1).name}</p>--%>
<%--    <form class="c_selectsort_form" action="${pageContext.request.contextPath}/catalog" method="get">--%>
<%--        <div>--%>
<%--            Category:--%>
<%--            <label>--%>
<%--                <select name="category" class="form-select">--%>
<%--                    <option value="0">All dishes</option>--%>
<%--                    <c:forEach var="category" items="${categories}">--%>
<%--                        <option ${param.category == category.id ? "selected" : ""} value="${category.id}">--%>
<%--                                ${category.name}--%>
<%--                        </option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </label>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            SortBy:--%>
<%--            <select name="sortBy" class="form-select">--%>
<%--                <c:forEach var="sort" items="${sortTypes}">--%>
<%--                    <option ${param.sortBy == sort.value ? "selected" : ""} value="${sort.value}">${sort.key}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            ShowOnPage:--%>
<%--            <select name="dishesOnPage" class="form-select">--%>
<%--                <c:forTokens items="5,10,15" delims="," var="item">--%>
<%--                    <option ${param.dishesOnPage == item ? "selected" : ""}>${item}</option>--%>
<%--                </c:forTokens>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--        <select name="page" style="display: none">--%>
<%--            <option value="0" selected></option>--%>
<%--        </select>--%>
<%--        <input class="btn btn-outline-secondary" type="submit" value="show"/>--%>
<%--    </form>--%>
<%--</nav>--%>


<section class="flex-container">
    <c:forEach var="cruise" items="${sessionScope.allCruises}">
        <div class="item">
            <img src="/parts/images/cruise${cruise.id}.jpg" alt="1">
            <div class="text">
                <div class="name">
                    <p>Cruise by "${cruise.ship.name}"
                        from ${cruise.ship.route.get(0).name} to ${cruise.ship.route.get(cruise.ship.route.size()-1).name}</p>
                </div>
                <div class="name">
                    <p>${cruise.startDate.toLocalDate().dayOfMonth} ${cruise.startDate.toLocalDate().month} ${cruise.startDate.toLocalDate().year}</p>
                </div>
                <div class="rote">
                    <p>
                        <c:forEach var="city" items="${cruise.ship.route}" varStatus="loop">
                                ${city.name} <c:if test="${!loop.last}"> - </c:if>
                        </c:forEach>
                    </p>
                </div>
                <div class="price">
                    Price ${cruise.price}
                </div>
            </div>
            <form action="controller?action=create-order" method="post">
                <input type="hidden" name="cruise_id" value="${cruise.id}">
                <div class="button-order">
                    <button type="submit" class="button-order">
                        <label>Order now</label>
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>
</section>