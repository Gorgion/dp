<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

s<input type="button" class="button action-url-button js-on-actionUrlButton" value="${labelText}" data-url="${url}" data-url-confirm="${confirmText}" />