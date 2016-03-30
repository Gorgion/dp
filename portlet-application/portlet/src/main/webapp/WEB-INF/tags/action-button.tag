<%-- =======================================================================
 *   IBA CZ Confidential
 *
 *    Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *    The source code for this program is not published or otherwise
 *    divested of its trade secrets.
 *
 * ======================================================================= --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="iba" uri="http://www.ibacz.eu/tags/iba" %>

<%@tag body-content="empty"%>
<%@tag description="Renderes action button." %>

<%@attribute name="labelText" required="false" type="java.lang.String" description="Button's label."%>
<%@attribute name="labelCode" required="false" type="java.lang.String" description="Button label's bundle key."%>
<%@attribute name="url" required="false" type="java.lang.String" description="URL to follow. If not filled, renderURL with no parameters will be used."%>
<%@attribute name="confirmText" required="false" type="java.lang.String" description="Confirm messge."%>
<%@attribute name="confirmCode" required="false" type="java.lang.String" description="Confirm message's bundle key."%>

<c:if test="${empty url}">
    <portlet:renderURL var="url"/>
</c:if>
<c:if test="${not empty labelCode}">
    <spring:message var="labelText" code="${labelCode}" />
</c:if>
<c:if test="${not empty confirmCode}">
    <spring:message var="confirmText" code="${confirmCode}" />
</c:if>

<input type="button" class="button action-url-button js-on-actionUrlButton" value="${iba:escapeHtml(labelText)}" data-url="${url}" data-url-confirm="${iba:escapeHtml(confirmText)}" />