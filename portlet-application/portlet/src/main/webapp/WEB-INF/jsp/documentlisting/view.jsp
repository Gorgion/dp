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
            <spring:message var="title" code="msg-document-header"/>
            <liferay-ui:header title="${title}"/>
            <c:if test="${not empty resultMsg}">
                <div><spring:message code="${resultMsg}"/></div>
            </c:if>

            <div class="buttons">
                <a class="btn btn-primary" href="${createUrl}"><spring:message code="msg-common-btn-new"/></a>
            </div>

            <c:if test="${empty allItems}">
                <div class="alert alert-info">
                    <spring:message code="msg-document-no-items"/>
                </div>
            </c:if>
            <table>
                <c:forEach var="item" items="${allItems}">
                    <portlet:renderURL var="detailUrl">
                        <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_DETAIL %>"/>
                        <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                    </portlet:renderURL>
                    <tr class="row-fluid">
                        <td>
                            <h3><a href="${detailUrl}"><c:out value="${item.title}"/></a></h3>
                            <p><c:out value="${item.description}"/></p>
                        </td>
                        <td>
                            <spring:message var="icoLabel" code="msg-common-btn-detail"/>
                            <span>
                            <liferay-ui:icon image="view" url="${detailUrl}" message="${icoLabel}" alt="${icoLabel}"/>
                                </span>

                            <portlet:renderURL var="editUrl">
                                <portlet:param name="<%= PARAM_PAGE %>" value="<%= PAGE_EDIT %>"/>
                                <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                            </portlet:renderURL>
                            <spring:message var="icoLabel" code="msg-common-btn-edit"/>
                            <span>
                            <liferay-ui:icon image="edit" url="${editUrl}" message="${icoLabel}" alt="${icoLabel}"/>
                                </span>

                            <portlet:actionURL name="<%= ACTION_DELETE %>" var="deleteUrl">
                                <portlet:param name="<%= PARAM_ID %>" value="${item.id}"/>
                            </portlet:actionURL>
                            <spring:message var="deleteMsg" code="msg-document-delete-question" arguments="${item.title}"/>

                            <span>
                                <a class="taglib-icon" href="${deleteUrl}" onclick="return confirm('${deleteMsg}')">
                                <spring:message var="icoLabel" code="msg-common-btn-delete"/>
                                <liferay-ui:icon image="delete" message="${icoLabel}" alt="${icoLabel}"/>
                                </a>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript">
    AUI().ready('node', function () {
        var roundDoc = parseInt(localStorage.getItem("roundDoc"));
        var arrayDoc = JSON.parse(localStorage.getItem("arrayDoc"));
        if (isNaN(roundDoc)) {
            roundDoc = 0;
        }
        if (arrayDoc == null) {
            arrayDoc = [];
        }
        if (roundDoc < 50) {
            arrayDoc.push(getDuration("DocumentListingPortlet_WAR_dpportletapplication"));
            localStorage.setItem("arrayDoc", JSON.stringify(arrayDoc));
            localStorage.setItem("roundDoc", ++roundDoc);

            setTimeout(function () {
                window.location.reload();
            }, 3000);
        }
    });
</script>