<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandler"%>
<%@ page import="cz.muni.fi.dp.iface.dto.DocumentDTO" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>

<%@include file="../init.jspf" %>

<%
    String displayStyle = "";
//            GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
//    long displayStyleGroupId = themeDisplay.scopeGroupId;//GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

//    long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId
//            (displayStyleGroupId, displayStyle);
%>
<h1>ADT</h1>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<aui:form action="${configurationURL}" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <aui:fieldset>
        <div class="display-template">

            <%
                TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(DocumentDTO.class.getName());
            %>

            <liferay-ui:ddm-template-selector
                    classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
                    displayStyle="<%= displayStyle %>"
                    displayStyleGroupId="${themeDisplay.scopeGroupId}"
                    refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                    showEmptyOption="<%= true %>"
            />
        </div>
    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />
    </aui:button-row>
</aui:form>