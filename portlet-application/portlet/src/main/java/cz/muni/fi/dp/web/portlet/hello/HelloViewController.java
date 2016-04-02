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
package cz.muni.fi.dp.web.portlet.hello;

import java.math.BigDecimal;
import java.util.Random;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

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
import cz.muni.fi.dp.web.portlet.hello.pto.DummyPto;
import eu.ibacz.tags.portletmessages.PortletMessages;

import static cz.muni.fi.dp.web.portlet.hello.HelloConstants.*;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class HelloViewController {

    private static final Logger LOG = Logger.getLogger(HelloViewController.class);

    @Autowired
    private DocumentService service;

    @RenderMapping
    public String defaultView(Model model, RenderRequest request, RenderResponse response) {

        LogMF.info(LOG, "Sample log message", null);
        LogMF.debug(LOG, "Sample log message with params: {0},{1},{2},\"{3}\",{4}", new Object[]{BigDecimal.ZERO, "Test", "param", "12*48", Integer.MAX_VALUE});

        Random randomizer = new Random();
        model.addAttribute(ATTR_ALL_ITEMS, service.getAllDummy());
        model.addAttribute(ATTR_DELETE_PERMISSION, randomizer.nextBoolean());

        return VIEW_MAIN;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_DETAIL)
    public String detail(
            @RequestParam(PARAM_ID) Long id,
            Model model, RenderRequest request, RenderResponse response) {

        model.addAttribute(ATTR_DUMMY_DTO, service.getDummyById(id));

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
            DocumentDTO s = service.getDummyById(id);
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

    @ActionMapping(ACTION_CREATE)
    public void createDummy(
            @Valid @ModelAttribute(ATTR_DUMMY_PTO) DummyPto pto,
            BindingResult result,
            ActionRequest request,
            ActionResponse response) {

        if (!result.hasErrors()) {
            DocumentDTO dto = new DocumentDTO(null, pto.getName(), pto.getEmail());

            long id = service.createDummy(dto);

            PortletMessages.addSuccessMsg(request, "msg-hello-dummy-created");

            response.setRenderParameter(PARAM_PAGE, PAGE_DETAIL);
            response.setRenderParameter(PARAM_ID, String.valueOf(id));
        } else {
            PortletMessages.addErrorMsg(request, "msg-common-err-form-contains-errors");

            response.setRenderParameter(PARAM_PAGE, PAGE_CREATE_FORM);
        }
    }

    @ActionMapping(ACTION_UPDATE)
    public void saveDummy(
            @Valid @ModelAttribute(ATTR_DUMMY_PTO) DummyPto pto,
            BindingResult result,
            ActionRequest request,
            ActionResponse response) {

        if (!result.hasErrors()) {
            DocumentDTO dto = service.getDummyById(pto.getId());
            dto.setName(pto.getName());
            dto.setEmail(pto.getEmail());

            service.updateDummy(dto);

            PortletMessages.addSuccessMsg(request, "msg-hello-dummy-saved");

            response.setRenderParameter(PARAM_PAGE, PAGE_DETAIL);
            response.setRenderParameter(PARAM_ID, String.valueOf(dto.getId()));
        } else {
            PortletMessages.addErrorMsg(request, "msg-common-err-form-contains-errors");

            response.setRenderParameter(PARAM_ID, pto.getId().toString());
            response.setRenderParameter(PARAM_PAGE, PAGE_EDIT_FORM);
        }
    }

    @ActionMapping(ACTION_DELETE)
    public void deleteDummy(
            @RequestParam(PARAM_ID) Long id,
            ActionRequest request,
            ActionResponse response) {

        DocumentDTO dto = service.getDummyById(id);
        service.deleteDummyById(id);

        PortletMessages.addSuccessMsg(request, "msg-hello-dummy-deleted", new String[]{dto.getName()});
    }
}
