<%-- ==========================================================================================
 *   IBA CZ Confidential
 *   Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *   The source code for this program is not published or otherwise divested of its trade secrets.
 * =============================================================================================

JSP pro výpis detailu.

Parametry:
    DummyDto documentDTO   - DTO objekt pro výpis
--%>
<%--@elvariable id="dummyDto" type="cz.muni.fi.dp.iface.dto.DocumentDTO"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*" %>

<portlet:renderURL var="backUrl" />

<div class="dp-application portlet-documentListing">
    <spring:message var="title" code="msg-document-detail-header" />
    <liferay-ui:header title="${title}" backURL="${backUrl}" />

    <c:if test="${not empty resultMsg}">
        <div><spring:message code="${resultMsg}" /></div>
    </c:if>

    <div class="document-wrapper">
        <h1>${documentDto.title}</h1>
        <c:if test="${not empty documentDto.description}">
            <p class="alert alert-info">${documentDto.description}</p>
        </c:if>
        <p><spring:message code="msg-document-detail-size" />: ${documentDto.size}</p>
        <p><spring:message code="msg-document-detail-type" />: ${documentDto.mimeType}</p>
        <p><spring:message code="msg-document-detail-download" />: <a href="${downloadUrl}">${downloadUrl}</a></p>
    </div>
</div>