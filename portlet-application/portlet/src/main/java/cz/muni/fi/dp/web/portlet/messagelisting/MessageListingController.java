package cz.muni.fi.dp.web.portlet.messagelisting;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import static cz.muni.fi.dp.web.portlet.messagelisting.MessageListingConstants.*;


/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class MessageListingController {

    @RenderMapping
    public String defaultView(Model model, RenderRequest request, RenderResponse response) throws SystemException {

        model.addAttribute(ATTR_ALL_MESSAGES, MBMessageLocalServiceUtil.getMBMessages(QueryUtil.ALL_POS, QueryUtil.ALL_POS));

        return VIEW_MAIN;
    }

}
