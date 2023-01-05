<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en" />
<fmt:setBundle basename="resources" />
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="css/style.css"%>
</style>


<section class="flex-container">
    <c:forEach var ="cruise" items="${sessionScope.allCruises}">
        <div class="item">
            <img src="/parts/images/cruise${cruise.id}.jpg" alt="1">
                <div class="text">
                    <div class="name">
                        <p>Cruise from ${cruise.startPort.name} to ${cruise.endPort.name}</p>
                    </div>
                    <div class="date">
                        <p>${cruise.startDate}</p>
                    </div>
                    <div class="price">
                        Price ${cruise.price}
                    </div>
                </div>
            <div class="link"></div>
        </div>
    </c:forEach>
</section>