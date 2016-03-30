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

<div class="iba-application fatal-error-page">

    <div class="portlet-msg-error">
        <spring:message code='msg-common-err-entity-not-found-error' />
    </div>

    <div class="buttons">
        <c:choose>
            <c:when test='<%= (request.getParameter("redirect") != null) %>'> <%-- Liferay Asset --%>
                <% String backRedir = request.getParameter("redirect"); %>
                <input type="button" class="button"
                       onclick="javascript:self.location='<%=backRedir%>';"
                       value="<spring:message code='msg-common-btn-back'/>" />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                       onclick="javascript:self.location='<portlet:renderURL />';"
                       value="<spring:message code='msg-common-btn-back'/>" />
            </c:otherwise>
        </c:choose>
    </div>

</div>
