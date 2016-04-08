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
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_CREATE_FORM %>" />
</portlet:renderURL>

<div id="${ns}helloView" class="iba-application portlet-hello">
    <iba-common:portletmessages />

    <liferay-ui:search-container emptyResultsMessage="msg-hello-no-items">
        <liferay-ui:search-container-results results="${allItems}" total="${fn:length(allItems)}" />

        <liferay-ui:search-container-row className="cz.muni.fi.dp.iface.dto.DocumentDTO" modelVar="item">
            <liferay-ui:search-container-column-text name="name">
                <c:out value="${item.name}" />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text name="email">
                <c:choose>
                    <c:when test="${!empty item.email}">
                        <c:out value="${item.email}" />
                    </c:when>
                    <c:otherwise>
                        <em><spring:message code="msg-common-empty-value" /></em>
                    </c:otherwise>
                </c:choose>
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text name="hello-label-actions">
                <portlet:renderURL var="detailUrl">
                    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_DETAIL %>" />
                    <portlet:param name="<%= PARAM_ID %>" value="${item.id}" />
                </portlet:renderURL>
                <portlet:renderURL var="editUrl">
                    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_EDIT_FORM %>" />
                    <portlet:param name="<%= PARAM_ID %>" value="${item.id}" />
                </portlet:renderURL>
                <spring:message var="icoLabel" code="msg-common-btn-detail" />
                <liferay-ui:icon image="view" url="${detailUrl}" message="${icoLabel}" alt="${icoLabel}" />
                <spring:message var="icoLabel" code="msg-common-btn-edit" />
                <liferay-ui:icon image="edit" url="${editUrl}" message="${icoLabel}" alt="${icoLabel}" />

                <%-- Podmienene zobrazenie tlacida s akciou --%>
                <c:if test="${deletePermission}">
                    <%-- Vygenervoanie  url ktore sa bude pre akciu pouzivat.--%>
                    <portlet:actionURL name="<%= ACTION_DELETE %>" var="deleteUrl">
                        <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                    </portlet:actionURL>
                    <spring:message var="deleteMsg" code="msg-hello-delete-question" arguments="${item.name}"/>
                    <%-- Je mozne vyuzit i nejaky vlastni TAG: <iba:action-button labelCode="msg-documentlisting-delete-btn" confirmText="${deleteMsg}" url="${deleteUrl}" /> --%>
                    <span>
                        <%-- Zadefinovanie naviazania eventu pomocou js-on- --%>
                        <a class="taglib-icon js-on-actionUrlButton" data-url="${deleteUrl}" data-url-confirm="${iba:escapeHtml(deleteMsg)}" href="javascript:;">
                            <spring:message var="icoLabel" code="msg-common-btn-delete" />
                            <liferay-ui:icon image="delete" message="${icoLabel}" alt="${icoLabel}" />
                        </a>
                    </span>
                </c:if>
            </liferay-ui:search-container-column-text>
        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator paginate="false" />
    </liferay-ui:search-container>

    <div class="buttons">
        <%-- Zadefinovanie naviazania eventu pomocou js-on- --%>
        <button class="js-on-goToPage" data-url="${createUrl}"><spring:message code="msg-common-btn-new" /></button>
    </div>
</div>

<script type="text/javascript">
    /*<![CDATA[*/
    /** volanie inicializacneho skriptu a predanie hodnout. */
    AUI().ready(function() {
        /*
        IBA.helloView({
            ns: "${ns}",
            deleteUrl: '${deleteUrl}', // url je prazdny string, ak delete nie je povoleny
            localization: { // lolizacni texty
                deleteQuestion: '<spring:message code="msg-hello-delete-question" arguments="-"/>'
            }
        });
        */
    });
    /*]]>*/
</script>