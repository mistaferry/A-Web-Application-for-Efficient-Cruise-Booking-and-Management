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
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png" alt="1">
                <div class="text">
                    <div class="name">
                        <p>${cruise.firstName}</p>
                    </div>
<%--                    <div class="size">--%>
<%--                        Size<br>--%>
<%--                        <a>XS</a>--%>
<%--                        <a>S</a>--%>
<%--                        <a>M</a>--%>
<%--                        <a>L</a>--%>
<%--                        <a>XL</a>--%>
<%--                        <a>XXL</a>--%>
<%--                    </div>--%>
                    <div class="price">
                        Price<br>
                        ${cruise.surname}
                    </div>
                </div>
            <div class="link"></div>
        </div>
    </c:forEach>
</section>