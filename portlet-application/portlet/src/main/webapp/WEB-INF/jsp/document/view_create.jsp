<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*" %>

<portlet:actionURL var="actionUrl" name="<%= ACTION_UPLOAD %>" />

<div class="iba-application portlet-hello">
    <liferay-ui:header title="${title}" backURL="${backUrl}" />

    <c:if test="${not empty resultMsg}">
        <div><spring:message code="${resultMsg}" /></div>
    </c:if>

    <%@include file="components/form.jspf" %>
</div>