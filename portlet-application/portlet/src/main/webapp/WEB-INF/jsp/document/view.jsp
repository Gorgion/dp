<%-- ==========================================================================================
 *   IBA CZ Confidential
 *   Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *   The source code for this program is not published or otherwise divested of its trade secrets.
 * =============================================================================================

JSP pro výpis seznamu objektů.

Parametry:
    List<DummyDto> allItems   - seznam DTO objektů pro výpis
--%>
<%--@elvariable id="allItems" type="java.util.List<cz.muni.fi.dp.iface.dto.DocumentDTO>"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>                                     

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*" %>

<portlet:renderURL var="createUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_UPLOAD %>" />
</portlet:renderURL>

<portlet:renderURL var="detailUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_DETAIL %>" />
    <portlet:param name="<%= PARAM_ID %>" value="${item.id}" />
</portlet:renderURL>

<div id="${ns}documentView" class="iba-application portlet-document">
    <c:if test="${not empty resultMsg}">
        <div><spring:message code="${resultMsg}" /></div>
    </c:if>

    <liferay-ui:search-container emptyResultsMessage="msg-document-no-items">
        <liferay-ui:search-container-results results="${allItems}" total="${fn:length(allItems)}" />

        <liferay-ui:search-container-row className="cz.muni.fi.dp.iface.dto.DocumentDTO" modelVar="item">
            <liferay-ui:search-container-column-text>
                <h1><a href="${detailUrl}"><c:out value="${item.title}" /></a></h1>
                <p><c:out value="${item.description}"/></p>
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text name="document-label-actions">
                <spring:message var="icoLabel" code="msg-common-btn-detail" />
                <liferay-ui:icon image="view" url="${detailUrl}" message="${icoLabel}" alt="${icoLabel}" />

                <portlet:actionURL name="<%= ACTION_DELETE %>" var="deleteUrl">
                    <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                </portlet:actionURL>
                <spring:message var="deleteMsg" code="msg-document-delete-question" arguments="${item.title}"/>
                <%-- Je mozne vyuzit i nejaky vlastni TAG: <iba:action-button labelCode="msg-documentlisting-delete-btn" confirmText="${deleteMsg}" url="${deleteUrl}" /> --%>
                <span>
                    <a class="taglib-icon" href="${deleteUrl}">
                        <spring:message var="icoLabel" code="msg-common-btn-delete" />
                        <liferay-ui:icon image="delete" message="${icoLabel}" alt="${icoLabel}" />
                    </a>
                </span>
            </liferay-ui:search-container-column-text>
        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator paginate="false" />
    </liferay-ui:search-container>

    <div class="buttons">
        <a class="btn btn-primary" href="${createUrl}"><spring:message code="msg-common-btn-new" /></a>
    </div>
</div>