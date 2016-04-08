package cz.muni.fi.dp.web.portlet.documentlisting;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.muni.fi.dp.iface.service.DocumentService;

import static cz.muni.fi.dp.web.portlet.documentlisting.DocumentListingConstants.*;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class DocumentListingController {

    @Autowired
    private DocumentService service;

    @RenderMapping
    public String defaultView(Model model, RenderRequest request, RenderResponse response) {

        model.addAttribute(ATTR_ALL_DOCUMENTS, service.getAllDocuments());

        return VIEW_MAIN;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_DETAIL)
    public String detail(@RequestParam(PARAM_ID) Long id, Model model, RenderRequest request, RenderResponse response) {

        model.addAttribute(ATTR_DOCUMENT_DTO, service.getDocumentById(id));

        return VIEW_DETAIL;
    }

}
