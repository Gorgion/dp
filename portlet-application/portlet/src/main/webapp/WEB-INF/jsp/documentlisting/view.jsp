<%--@elvariable id="allItems" type="java.util.List<cz.muni.fi.dp.iface.dto.DocumentDTO>"--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*" %>
<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="cz.muni.fi.dp.iface.dto.DocumentDTO" %>
<%@ page import="java.util.List" %>

<%
    String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
    long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

    long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>
<portlet:renderURL var="createUrl">
    <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_UPLOAD %>"/>
</portlet:renderURL>

<div id="${ns}documentView" class="dp-application portlet-document">
    <c:choose>
        <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
            <%
                List<DocumentDTO> entities = (List<DocumentDTO>) request.getAttribute("allItems");
            %>

            <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, entities) %>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty resultMsg}">
                <div><spring:message code="${resultMsg}"/></div>
            </c:if>

            <div class="buttons">
                <a class="btn btn-primary" href="${createUrl}"><spring:message code="msg-common-btn-new"/></a>
            </div>

            <liferay-ui:search-container emptyResultsMessage="msg-document-no-items">
                <liferay-ui:search-container-results results="${allItems}" total="${fn:length(allItems)}"/>

                <liferay-ui:search-container-row className="cz.muni.fi.dp.iface.dto.DocumentDTO" modelVar="item">
                    <portlet:renderURL var="detailUrl">
                        <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_DETAIL %>"/>
                        <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                    </portlet:renderURL>

                    <liferay-ui:search-container-column-text>
                        <h3><a href="${detailUrl}"><c:out value="${item.title}"/></a></h3>
                        <p><c:out value="${item.description}"/></p>
                    </liferay-ui:search-container-column-text>

                    <liferay-ui:search-container-column-text name="document-label-actions">
                        <spring:message var="icoLabel" code="msg-common-btn-detail"/>
                        <liferay-ui:icon image="view" url="${detailUrl}" message="${icoLabel}" alt="${icoLabel}"/>

                        <portlet:renderURL var="editUrl">
                            <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_EDIT %>"/>
                            <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                        </portlet:renderURL>
                        <spring:message var="icoLabel" code="msg-common-btn-edit"/>
                        <liferay-ui:icon image="edit" url="${editUrl}" message="${icoLabel}" alt="${icoLabel}"/>

                        <portlet:actionURL name="<%= ACTION_DELETE %>" var="deleteUrl">
                            <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                        </portlet:actionURL>
                        <spring:message var="deleteMsg" code="msg-document-delete-question" arguments="${item.title}"/>

                <span>
                    <a class="taglib-icon" href="${deleteUrl}">
                        <spring:message var="icoLabel" code="msg-common-btn-delete"/>
                        <liferay-ui:icon image="delete" message="${icoLabel}" alt="${icoLabel}"/>
                    </a>
                </span>
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>

                <liferay-ui:search-iterator paginate="false"/>
            </liferay-ui:search-container>
        </c:otherwise>
    </c:choose>
</div>