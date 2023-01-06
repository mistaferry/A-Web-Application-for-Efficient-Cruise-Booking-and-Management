<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="css/style.css"%>
</style>


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