<%-- ==========================================================================================
 *   IBA CZ Confidential
 *   Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *   The source code for this program is not published or otherwise divested of its trade secrets.
 * =============================================================================================

JSP pro výpis formuláře pro editaci.

Parametry:
    DummyPto dummyPto   - PTO objekt formuláře
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.hello.HelloConstants.*" %>

<portlet:actionURL var="actionUrl" name="<%= ACTION_UPDATE%>" />
<portlet:renderURL var="addEmailUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_EDIT_FORM %>"/>
    <portlet:param name="<%= PARAM_ID %>" value="${dummyPto.id}" />
    <portlet:param name="<%= PARAM_ADD_EMAIL %>" value="true" />
</portlet:renderURL>
<portlet:renderURL var="backUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_DETAIL %>" />
    <portlet:param name="<%= PARAM_ID %>" value="${dummyPto.id}" />
</portlet:renderURL>

<div class="iba-application portlet-hello">
    <iba-common:portletmessages />

    <c:set var="heading" value="msg-hello-dummy-update" />
    <%@include file="components/form.jspf" %>
</div>