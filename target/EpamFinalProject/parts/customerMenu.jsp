<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="en"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<style>
    <%@ include file="/parts/css/newStylr.css"%>
</style>

<div>
    <nav class="nav-enter customer-nav">
        <a id="company-name"><fmt:message key="company.name"/></a>
        <a>
            <form method="POST" class="d-flex" >
                <label>
                    <select name="language" onchange='submit()'>
                        <option value="en" ${sessionScope.language eq 'en' ? 'selected' : ''}>en
                        </option>
                        <option value="nl" ${sessionScope.language eq 'nl' ? 'selected' : ''}>nl
                        </option>
                    </select>
                </label>
            </form>
        </a>
        <a href="controller?action=view-cruises&startDay=All&duration=All&page=0&cruisePerPage=5"><fmt:message key="nav.catalog"/></a>
        <a><fmt:message key="nav.my.orders"/></a>
        <a href="profile.jsp"><fmt:message key="nav.account"/></a>
        <a><fmt:message key="sign.out"/></a>
    </nav>
</div>
