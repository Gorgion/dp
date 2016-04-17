package cz.muni.fi.dp.web.portlet.documentlisting.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
import cz.muni.fi.dp.iface.dto.DocumentDTO;

/**
 * Created by Tomas on 17.04.2016.
 */
public class DocumentListingPortletDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {
    @Override
    public String getClassName() {
        return DocumentDTO.class.getName();
    }

    @Override
    public String getName(Locale locale) {
        return "DocumentListingPortlet";
    }

    @Override
    public String getResourceName() {
        return "DocumentListingPortlet_WAR_dpportletapplication";
    }

    public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
        long classPK, String language, Locale locale)
        throws Exception {

        Map<String, TemplateVariableGroup> templateVariableGroups =
            super.getTemplateVariableGroups(classPK, language, locale);

        TemplateVariableGroup templateVariableGroup =
            templateVariableGroups.get("fields");

        templateVariableGroup.empty();

        templateVariableGroup.addCollectionVariable(
            "entities", List.class, PortletDisplayTemplateConstants.ENTRIES,
            "entity", DocumentDTO.class, "curEntity", "");
        return templateVariableGroups;
    }
}
