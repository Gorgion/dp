<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*" %>

<portlet:actionURL var="actionUrl" name="<%= ACTION_UPDATE %>" />
<portlet:renderURL var="backUrl" />

<div class="dp-application portlet-documentListing">
    <spring:message var="title" code="msg-document-update-title" />
    <liferay-ui:header title="${title}" backURL="${backUrl}" />

    <c:if test="${not empty resultMsg}">
        <div><spring:message code="${resultMsg}" /></div>
    </c:if>
    <c:set value="true" var="asEdit" />
    <%@include file="components/form.jspf" %>
</div>