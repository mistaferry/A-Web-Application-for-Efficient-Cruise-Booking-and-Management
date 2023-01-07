<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="css/newStylr.css"%>
</style>

<form method="GET" action="controller" class="flex">
    <input type="hidden" name="action" value="view-cruises">
    <div>
        <nav class="nav2">
            <a>
                <label>
                    <select name="duration" class="select">
                        <option value="All">Duration</option>
                        <option value="All" ${param.duration eq "All" ? "selected" : ""}>All</option>
                        <option value="1-4" ${param.duration eq "1-4" ? "selected" : ""}>1-4</option>
                        <option value="5-8" ${param.duration eq "5-8" ? "selected" : ""}>5-8</option>
                        <option value="9-12" ${param.duration eq "9-12" ? "selected" : ""}>9-12</option>
                    </select>
                </label>
            </a>
            <a>
                <label>
                    <input class="select" type="date" name="startDay"
                           value="${not empty param.startDay ? param.startDay : ""}"/>
                </label>
            </a>
            <a>
                <div>
                    <button id="save-button" type="submit">Show</button>
                </div>
            </a>
        </nav>
    </div>
</form>

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