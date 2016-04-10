package cz.muni.fi.dp.iface.service;

import java.util.List;

import com.liferay.portal.service.ServiceContext;
import cz.muni.fi.dp.iface.dto.DocumentDTO;

/**
 * Service providing access to the data-access-layer.
 */
public interface DocumentService {

    List<DocumentDTO> getAllDocuments();

    DocumentDTO getDocumentById(Long id);

    long createDocument(DocumentDTO documentDTO, ServiceContext serviceContext);

    void updateDocument(DocumentDTO documentDTO, ServiceContext serviceContext);

    void deleteDocumentById(Long id);
}
