package cz.muni.fi.dp.web.portlet.messagelisting;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

/**
 * Created by Tomas on 17.04.2016.
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {
    @Override
    public void processAction(PortletConfig portletConfig,
                              ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {
        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences prefs = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        String displayStyle = ParamUtil.getString(actionRequest, "displayStyle");
        prefs.setValue("displayStyle", displayStyle);
        prefs.store();
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
}

