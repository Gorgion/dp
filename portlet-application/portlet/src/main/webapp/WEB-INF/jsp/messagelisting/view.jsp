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

            <c:if test="${empty allItems}">
                <div class="alert-info alert">
                    <spring:message code="msg-message-no-items" />
                </div>
            </c:if>
            <table>
                <c:forEach var="item" items="${allItems}">
                    <tr>
                        <td>
                            <h3><c:out value="${item.subject}"/></h3>
                            <p><c:out value="${item.body}"/></p>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript">
    AUI().ready('node', function () {
        var roundMsg = parseInt(localStorage.getItem("roundMsg"));
        var arrayMsg = JSON.parse(localStorage.getItem("arrayMsg"));
        var arrayAP = JSON.parse(localStorage.getItem("arrayAP"));
        if(isNaN(roundMsg)) {
            roundMsg = 0;
        }
        if (arrayMsg == null) {
            arrayMsg = [];
        }
        if (arrayAP == null) {
            arrayAP = [];
        }
        if(roundMsg < 50) {
            arrayMsg.push(getDuration("MessageListingPortlet_WAR_dpportletapplication"));
            arrayAP.push(getDuration("Asset Publisher"));
            localStorage.setItem("arrayMsg", JSON.stringify(arrayMsg));
            localStorage.setItem("arrayAP", JSON.stringify(arrayAP));
            localStorage.setItem("roundMsg", ++roundMsg);

            setTimeout(function () {
                window.location.reload();
            }, 3000);
        }
    });
</script>