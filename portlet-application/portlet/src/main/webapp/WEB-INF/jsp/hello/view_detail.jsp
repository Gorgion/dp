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

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.HelloConstants.*" %>

<portlet:renderURL var="editUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_EDIT_FORM %>" />
    <portlet:param name="<%= PARAM_ID %>" value="${documentDTO.id}" />
</portlet:renderURL>
<portlet:renderURL var="backUrl" />

<div class="iba-application portlet-hello">
    <iba-common:portletmessages />
    
    <iba-detail:detail modelAttribute="<%= ATTR_DUMMY_DTO %>" headingCode="msg-hello-dummy-detail">
        <iba-detail:value path="name" />
        <iba-detail:value path="email" />
    </iba-detail:detail>

    <div class="buttons">
        <button class="js-on-goToPage" data-url="${editUrl}"><spring:message code="msg-common-btn-edit" /></button>
        <button class="js-on-goToPage" data-url="${backUrl}"><spring:message code="msg-common-btn-back" /></button>
    </div>
</div>