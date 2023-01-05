<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl" />
<fmt:setBundle basename="resources" />

<div class="c_dishes">
    <c:forEach var="dish" items="${dishes}">
        <div class="cruise_container">
            <img src="src/main/webapp/images/cruise1.jpg"
                 class="cruise_image"/>
            <div>
                <p>${dish.name}</p>
                <p class="dish_description">&ensp;kdjfbvsjnl</p>
                <p class="dish_weight">Weight: </p>
                <p class="dish_price">Price</p>
            </div>
        </div>
    </c:forEach>
</div>
