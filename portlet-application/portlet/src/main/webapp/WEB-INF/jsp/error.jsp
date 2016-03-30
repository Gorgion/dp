<%-- =======================================================================
 *   IBA CZ Confidential
 *
 *    © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *    The source code for this program is not published or otherwise
 *    divested of its trade secrets.
 *
 * =======================================================================

JSP pro výpis chyby.

Parametry:
    Exception exception      - Objekt vyjímky.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="init.jspf"%>

<div class="portlet-msg-error">
    <spring:message code='msg-common-err-fatal'/>

    <div style="color: gray; margin-top: 5px;">
        <fmt:formatDate value="<%=new java.util.Date()%>" pattern="dd.MM.yyyy HH:mm:ss z" />
    </div>

    <c:if test="${not empty exception}">
        <div style="display: none;">
            <%
                do {
            %>
            <pre><c:out value="${exception}"/></pre>
            <pre><c:forEach var="st" items="${exception.stackTrace}" varStatus="loop"><c:if test="${loop.index < 30}">&nbsp;&nbsp;<c:out value="${st}"/><br/></c:if><c:if test="${loop.index == 30}">&nbsp;&nbsp;... and more ...<br/></c:if></c:forEach></pre>
            <c:set var="exception" value="${exception.cause}" />
            <%
                } while (pageContext.getAttribute("exception") != null);
            %>
        </div>
    </c:if>

    <div class="buttons">
        <c:choose>
            <c:when test='<%= (request.getParameter("redirect") != null) %>'> <%-- Liferay Asset --%>
                <% String backRedir = request.getParameter("redirect"); %>
                <input type="button" class="button"
                       onclick="javascript:self.location='<%=backRedir%>';"
                       value="<spring:message code='msg-common-btn-ok'/>" />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                       onclick="javascript:self.location='<portlet:renderURL />';"
                       value="<spring:message code='msg-common-btn-ok'/>" />
            </c:otherwise>
        </c:choose>
    </div>

</div>
