<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="../init.jspf" %>

<%@page import="static cz.muni.fi.dp.web.portlet.messagelisting.MessageListingConstants.*" %>
<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portlet.messageboards.model.MBMessage" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>

<%
    String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
    long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

    long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<div id="${ns}messageView" class="dp-application portlet-message">
    <c:choose>
        <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
            <%
                List<MBMessage> entities = MBMessageLocalServiceUtil.getMBMessages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            %>

            <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, entities) %>
        </c:when>
        <c:otherwise>
            <spring:message var="title" code="msg-message-header"/>
            <liferay-ui:header title="${title}" />
            <c:if test="${not empty resultMsg}">
                <div><spring:message code="${resultMsg}"/></div>
            </c:if>

            <liferay-ui:search-container emptyResultsMessage="msg-message-no-items">
                <liferay-ui:search-container-results results="${allItems}" total="${fn:length(allItems)}"/>

                <liferay-ui:search-container-row className="com.liferay.portlet.messageboards.model.MBMessage" modelVar="item">

                    <liferay-ui:search-container-column-text>
                        <h3><c:out value="${item.subject}"/></h3>
                        <p><c:out value="${item.body}"/></p>
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>

                <liferay-ui:search-iterator paginate="false"/>
            </liferay-ui:search-container>
        </c:otherwise>
    </c:choose>
</div>