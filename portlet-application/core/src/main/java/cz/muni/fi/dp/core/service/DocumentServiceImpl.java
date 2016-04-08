package cz.muni.fi.dp.core.service;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import cz.muni.fi.dp.iface.common.ApplicationException;
import cz.muni.fi.dp.iface.dto.DocumentDTO;
import cz.muni.fi.dp.iface.service.DocumentService;
import org.apache.commons.lang.Validate;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service providing access to the data-access-layer.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOG = Logger.getLogger(DocumentServiceImpl.class);

    @Autowired
    private DLFileEntryLocalService dlFileEntryLocalService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "document.all.CACHE")
    public List<DocumentDTO> getAllDocuments() {
        List<DLFileEntry> entities;
        try {
            entities = dlFileEntryLocalService.getDLFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (SystemException e) {
            LogMF.error(LOG, e, "Error occurred during retrieving documents. errorMsg={0}", new Object[]{e.getMessage()});
            throw new ApplicationException(e);
        }

        List<DocumentDTO> dtos = new ArrayList<>(entities.size());
        for (DLFileEntry entity : entities) {
            DocumentDTO dto = convertToDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "document.byId.CACHE", key = "#id")
    public DocumentDTO getDocumentById(Long id) {
        Validate.notNull(id);

        DLFileEntry documentEntity = null;
        try {
            documentEntity = dlFileEntryLocalService.getDLFileEntry(id);
        } catch (PortalException | SystemException e) {
            LogMF.error(LOG, e, "Error occurred during retrieving the document. id={0}, errorMsg={1}", new Object[]{id, e.getMessage()});
            throw new ApplicationException(e);
        }
        DocumentDTO dto = convertToDto(documentEntity);

        return dto;
    }

    private DocumentDTO convertToDto(DLFileEntry documentEntity) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(documentEntity.getFileEntryId());

        return dto;
    }
}
