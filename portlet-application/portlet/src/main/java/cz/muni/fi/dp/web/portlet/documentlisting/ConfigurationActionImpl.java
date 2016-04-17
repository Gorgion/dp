package cz.muni.fi.dp.web.portlet.documentlisting;

import java.io.IOException;
import javax.portlet.*;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Created by Tomas on 17.04.2016.
 */
//@Controller
//@RequestMapping("EDIT")
public class ConfigurationActionImpl extends DefaultConfigurationAction {
    @Override
    public void processAction(PortletConfig portletConfig,
                              ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {
        // TODO Auto-generated method stub
        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        javax.portlet.PortletPreferences prefs = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        String displayStyle = ParamUtil.getString(actionRequest, "displayStyle");
        prefs.setValue("displayStyle", displayStyle);
        prefs.store();
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

//    public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
//        return "/documentlisting/config";
//    }
}

//    public class ConfigurationActionImpl  {
    /*
    @ActionMapping
//    public void processAction(PortletConfig portletConfig, ActionRequest request, ActionResponse response) throws Exception {
    public void processAction(ActionRequest request, ActionResponse response) throws Exception {

        String portletResource = ParamUtil.getString(request, "portletResource");
        javax.portlet.PortletPreferences prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
        String displayStyle = ParamUtil.getString(request, "displayStyle");
        prefs.setValue("displayStyle", displayStyle);
        prefs.store();
    }

//    @Override
    @RenderMapping
//    public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        return "/documentlisting/config";
    }
}*/
