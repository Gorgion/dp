/**
 *  ===========================================================================
 *  IBA CZ Confidential
 *
 *  © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *  The source code for this program is not published or otherwise
 *  divested of its trade secrets.
 *  ===========================================================================
 *
 */
package cz.muni.fi.dp.web.portlet.documentlisting;

import java.math.BigDecimal;
import java.util.Random;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
//import javax.validation.Valid;

import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.muni.fi.dp.iface.dto.DocumentDTO;
import cz.muni.fi.dp.iface.service.DocumentService;
import cz.muni.fi.dp.web.portlet.documentlisting.pto.DummyPto;

import static cz.muni.fi.dp.web.portlet.documentlisting.HelloConstants.*;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class DocumentListingController {

    private static final Logger LOG = Logger.getLogger(DocumentListingController.class);

    @Autowired
    private DocumentService service;

    @RenderMapping
    public String defaultView(Model model, RenderRequest request, RenderResponse response) {

        LogMF.info(LOG, "Sample log message", null);
        LogMF.debug(LOG, "Sample log message with params: {0},{1},{2},\"{3}\",{4}", new Object[]{BigDecimal.ZERO, "Test", "param", "12*48", Integer.MAX_VALUE});

        Random randomizer = new Random();
        model.addAttribute(ATTR_ALL_ITEMS, service.getAllDocuments());
        model.addAttribute(ATTR_DELETE_PERMISSION, randomizer.nextBoolean());

        return VIEW_MAIN;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_DETAIL)
    public String detail(
            @RequestParam(PARAM_ID) Long id,
            Model model, RenderRequest request, RenderResponse response) {

        model.addAttribute(ATTR_DUMMY_DTO, service.getDocumentById(id));

        return VIEW_DETAIL;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_CREATE_FORM)
    public String createForm(
            @RequestParam(value = PARAM_ADD_EMAIL, required = false, defaultValue = "false") boolean addEmail,
            Model model, RenderRequest request, RenderResponse response) {

        if (!model.containsAttribute(ATTR_DUMMY_PTO)) {
            model.addAttribute(ATTR_DUMMY_PTO, new DummyPto());
        }

        model.addAttribute(ATTR_ADD_EMAIL, addEmail);

        return VIEW_CREATE_FORM;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_EDIT_FORM)
    public String editForm(
            @RequestParam(PARAM_ID) Long id,
            @RequestParam(value = PARAM_ADD_EMAIL, required = false, defaultValue = "false") boolean addEmail,
            Model model, RenderRequest request, RenderResponse response) {

        if (!model.containsAttribute(ATTR_DUMMY_PTO)) {
            DocumentDTO s = service.getDocumentById(id);
            DummyPto pto = convertToPto(s);
            model.addAttribute(ATTR_DUMMY_PTO, pto);
        }
        model.addAttribute(ATTR_ADD_EMAIL, addEmail);

        return VIEW_EDIT_FORM;
    }

    private DummyPto convertToPto(DocumentDTO s) {
        DummyPto pto = new DummyPto();
        pto.setId(s.getId());
        pto.setName(s.getName());
        pto.setEmail(s.getEmail());
        return pto;
    }
}
