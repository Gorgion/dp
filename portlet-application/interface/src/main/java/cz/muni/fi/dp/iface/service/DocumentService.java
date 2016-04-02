package cz.muni.fi.dp.iface.service;

import java.util.List;

import cz.muni.fi.dp.iface.dto.DocumentDTO;

/**
 * Service providing access to the data-access-layer.
 */
public interface DocumentService {

    List<DocumentDTO> getAllDocuments();

    DocumentDTO getDocumentById(Long id);

    long createDocument(DocumentDTO DocumentDTO);

    void updateDocument(DocumentDTO DocumentDTO);

    void deleteDocumentById(Long id);
}
