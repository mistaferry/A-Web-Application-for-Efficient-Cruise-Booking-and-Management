<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination justify-content-center">
    <c:if test="${requestScope.end > 3}">
        <li class="page-item">
            <a class="page-link link-dark" href="${requestScope.href}offset=0&records=${requestScope.records}">
                1
            </a>
        </li>
        <c:if test="${requestScope.end > 4}">
            <li class="page-item">
                <c:set var="currentOffset" value="${(requestScope.start - 2) * requestScope.records}"/>
                <a class="page-link link-dark"
                   href="${requestScope.href}offset=${currentOffset}&records=${requestScope.records}">
                    ...
                </a>
            </li>
        </c:if>
    </c:if>
    <c:forEach var="page" begin="${requestScope.start}" end="${requestScope.end}">
        <li class="page-item">
            <c:set var="currentOffset" value="${(page - 1) * requestScope.records}"/>
            <a class="page-link ${requestScope.currentPage eq page ? 'dark-active' : 'link-dark'}"
               href="${requestScope.href}offset=${currentOffset}&records=${requestScope.records}">
                    ${page}
            </a>
        </li>
    </c:forEach>
    <c:if test="${requestScope.end < requestScope.pages}">
        <c:if test="${requestScope.end + 1 < requestScope.pages}">
            <li class="page-item">
                <c:set var="currentOffset" value="${(requestScope.end) * requestScope.records}"/>
                <a class="page-link link-dark"
                   href="${requestScope.href}offset=${currentOffset}&records=${requestScope.records}">
                    ...
                </a>
            </li>
        </c:if>
        <li class="page-item">
            <a class="page-link link-dark"
               href="${requestScope.href}offset=${(requestScope.pages - 1) * requestScope.records}&records=${requestScope.records}">
                    ${requestScope.pages}
            </a>
        </li>
    </c:if>
</ul>