<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>


<nav class="c_pagination">
    <ul class="pagination justify-content-end">
        <li class="page-item ${param.page > 0 ? "" : "disabled"}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/catalog?category=${param.category}&sortBy=${param.sortBy}&page=${param.page-1}&dishesOnPage=${param.dishesOnPage}"
               tabindex="-1">
                Previous
            </a>
        </li>
        <c:forEach var="num" begin="0" end="${sessionScope.pageAmount}">
            <li class="page-item ${param.page == num ? "active" : ""}">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/catalog?category=${param.category}&sortBy=${param.sortBy}&page=${num}&dishesOnPage=${param.dishesOnPage}">
                        ${num+1}
                </a>
            </li>
        </c:forEach>
        <li class="page-item ${param.page < sessionScope.pageAmount ? "" : "disabled"}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/catalog?category=${param.category}&sortBy=${param.sortBy}&page=${param.page+1}&dishesOnPage=${param.dishesOnPage}">
                Next
            </a>
        </li>
    </ul>
</nav>