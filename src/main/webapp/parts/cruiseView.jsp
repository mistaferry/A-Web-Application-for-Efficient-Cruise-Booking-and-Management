<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="css/style.css"%>
</style>

<div>
    <nav class="nav2">
        <a>
            <label>
                <select  class="select">
                    <option selected disabled hidden>Duration</option>
                    <option value="1-4">1-4</option>
                    <option value="5-8">5-8</option>
                    <option value="9-12">9-12</option>
                </select>
            </label>
        </a>
        <a>
            <label>
                <input class="select" type="date" name="startDay" />
            </label>
        </a>
    </nav>
</div>
<%--<nav class="c_header">--%>
<%--&lt;%&ndash;    <p class="c_category_name">${empty param.category || param.category == 0 ? "All dishes" : categories.get(param.category-1).name}</p>&ndash;%&gt;--%>
<%--    <form class="c_selectsort_form" &lt;%&ndash;action="${pageContext.request.contextPath}/catalog"&ndash;%&gt; method="get">--%>
<%--        <div>--%>
<%--            <label>--%>

<%--&lt;%&ndash;                <select name="category" class="form-select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <option value="0">All dishes</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <c:forEach var="category" items="${categories}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <option ${param.category == category.id ? "selected" : ""} value="${category.id}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                ${category.name}&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </select>&ndash;%&gt;--%>
<%--            </label>--%>
<%--        </div>--%>
<%--&lt;%&ndash;        <div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            Duration:&ndash;%&gt;--%>
<%--&lt;%&ndash;            <label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <select name="category" class="form-select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <option value="0">All cruises</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <c:forEach var="category" items="${categories}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <option ${param.category == category.id ? "selected" : ""} value="${category.id}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                ${category.name}&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </select>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            SortBy:&ndash;%&gt;--%>
<%--&lt;%&ndash;            <select name="sortBy" class="form-select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <c:forEach var="sort" items="${sortTypes}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <option ${param.sortBy == sort.value ? "selected" : ""} value="${sort.value}">${sort.key}</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </select>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            ShowOnPage:&ndash;%&gt;--%>
<%--&lt;%&ndash;            <select name="dishesOnPage" class="form-select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <c:forTokens items="5,10,15" delims="," var="item">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <option ${param.dishesOnPage == item ? "selected" : ""}>${item}</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:forTokens>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </select>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <select name="page" style="display: none">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <option value="0" selected></option>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </select>&ndash;%&gt;--%>
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