<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandler"%>
<%@ page import="com.liferay.portlet.messageboards.model.MBMessage" %>

<%@include file="../init.jspf" %>

<%
    String displayStyle = "";
%>
<h1>ADT</h1>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<aui:form action="${configurationURL}" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <aui:fieldset>
        <div class="display-template">

            <%
                TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(MBMessage.class.getName());
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