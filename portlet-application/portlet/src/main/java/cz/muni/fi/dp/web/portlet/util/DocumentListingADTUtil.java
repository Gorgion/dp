package cz.muni.fi.dp.web.portlet.util;

import java.util.List;

import cz.muni.fi.dp.iface.dto.DocumentDTO;
import cz.muni.fi.dp.iface.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tomas on 17.04.2016.
 */
@Component
public class DocumentListingADTUtil {
    @Autowired
    private DocumentService documentService;

    public List<DocumentDTO> getAllDocuments(){
        return documentService.getAllDocuments();
    }

}
