<%
/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="init.jspf"%>

<%@ page import="eu.ibacz.tags.portletmessages.PortletMessages" %>


<c:choose>
    <c:when test="<%=themeDisplay.isLifecycleRender()%>">
        <% PortletMessages.addErrorMsg(renderRequest, "msg-common-err-session-attr-not-found"); %>
    </c:when>
    <c:when test="<%=themeDisplay.isLifecycleAction()%>">
        <% PortletMessages.addErrorMsg(actionRequest, "msg-common-err-session-attr-not-found"); %>
    </c:when>
    <c:when test="<%=themeDisplay.isLifecycleResource()%>">
        <% PortletMessages.addErrorMsg(resourceRequest, "msg-common-err-session-attr-not-found"); %>
    </c:when>
</c:choose>


<c:choose>
    <c:when test="<%=themeDisplay.isStatePopUp()%>">
        <script type="text/javascript" language="JavaScript">
        /* <![CDATA[ */
            window.top.location = "<portlet:renderURL />";
        /* ]]> */
        </script>
    </c:when>
    <c:otherwise>
        <script type="text/javascript" language="JavaScript">
        /* <![CDATA[ */
            window.location = "<portlet:renderURL />";
        /* ]]> */
        </script>
    </c:otherwise>
</c:choose>
