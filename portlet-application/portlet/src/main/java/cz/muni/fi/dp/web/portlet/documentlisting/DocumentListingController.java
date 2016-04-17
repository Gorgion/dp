package cz.muni.fi.dp.web.portlet.documentlisting;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import cz.muni.fi.dp.iface.dto.DocumentDTO;
import cz.muni.fi.dp.web.portlet.documentlisting.pto.DocumentPTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
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
    public String detail(@RequestParam(PARAM_ID) Long id, Model model, RenderRequest request, RenderResponse response) throws SystemException, PortalException {

        model.addAttribute(ATTR_DOCUMENT_DTO, service.getDocumentById(id));
        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(id);
        String url = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), getThemeDisplay(request), "", true, true);
        model.addAttribute(ATTR_DOWNLOAD_URL, url);

        return VIEW_DETAIL;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_UPLOAD)
    public String uploadPage(RenderRequest request, RenderResponse response, Model model) {
        model.addAttribute(ATTR_DOCUMENT_PTO, new DocumentPTO());

        return VIEW_UPLOAD;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_EDIT)
    public String editPage(@RequestParam(PARAM_ID) Long id, RenderRequest request, RenderResponse response, Model model) {
        DocumentDTO dto = service.getDocumentById(id);
        DocumentPTO pto = convert(dto);

        model.addAttribute(ATTR_DOCUMENT_PTO, pto);

        return VIEW_EDIT;
    }

    @ActionMapping(ACTION_UPLOAD)
    public void uploadAction(@ModelAttribute(PARAM_DOCUMENT_PTO) DocumentPTO pto, BindingResult result, Model model, ActionRequest request, ActionResponse response) throws SystemException, PortalException, IOException {

        if (result.hasErrors()) {
            model.addAttribute(ATTR_DOCUMENT_PTO, pto);
            response.setRenderParameter(PARAM_PAGE, PAGE_UPLOAD);
            return;
        }

        Long userId = PortalUtil.getUserId(request);
        ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);
        Long repositoryId = PortalUtil.getScopeGroupId(request);

        DocumentDTO dto = new DocumentDTO();

        dto.setTitle(pto.getTitle());
        dto.setDescription(pto.getDescription());
        dto.setIs(pto.getFile().getInputStream());
        dto.setSize(pto.getFile().getSize());
        dto.setMimeType(pto.getFile().getContentType());
        dto.setSourceFileName(pto.getFile().getOriginalFilename());
        dto.setChangeLog("");
        dto.setRepositoryId(repositoryId);
        dto.setFolderId(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
        dto.setUserId(userId);

        long fileId = service.createDocument(dto, serviceContext);
        response.setRenderParameter(PARAM_ID, String.valueOf(fileId));

    }

    @ActionMapping(ACTION_UPDATE)
    public void uploadAction(@ModelAttribute(PARAM_DOCUMENT_PTO) DocumentPTO pto, BindingResult result, ActionRequest request, ActionResponse response) throws SystemException, PortalException {
        DocumentDTO documentDTO = service.getDocumentById(pto.getId());
        documentDTO.setTitle(pto.getTitle());
        documentDTO.setDescription(pto.getDescription());

        ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);

        service.updateDocument(documentDTO, serviceContext);

        response.setRenderParameter("resultMsg", "msg-document-updated");
    }

    @ActionMapping(ACTION_DELETE)
    public void deleteAction(@RequestParam(PARAM_ID) Long id, ActionRequest request, ActionResponse response) {
        service.deleteDocumentById(id);

        response.setRenderParameter("resultMsg", "msg-document-deleted");
    }

    private DocumentPTO convert(DocumentDTO dto) {
        DocumentPTO pto = new DocumentPTO();
        pto.setId(dto.getId());
        pto.setTitle(dto.getTitle());
        pto.setDescription(dto.getDescription());
        return pto;
    }

    private ThemeDisplay getThemeDisplay(RenderRequest renderRequest){
        return (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
    }
}
