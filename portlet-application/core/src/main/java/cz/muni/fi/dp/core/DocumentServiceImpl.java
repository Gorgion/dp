package cz.muni.fi.dp.core;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalService;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import cz.muni.fi.dp.iface.common.ApplicationException;
import cz.muni.fi.dp.iface.dto.DocumentDTO;
import cz.muni.fi.dp.iface.service.DocumentService;
import org.apache.commons.lang.Validate;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service providing access to the data-access-layer.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DLFileEntryLocalService dlFileEntryLocalService;
    @Autowired
    private DLAppLocalService dlAppLocalService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "document.all.CACHE")
    public List<DocumentDTO> getAllDocuments() {
        try {
            List<DLFileEntry> entities = dlFileEntryLocalService.getDLFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            List<DocumentDTO> dtos = new ArrayList<>(entities.size());
            for (DLFileEntry entity : entities) {
                DocumentDTO dto = convertToDto(entity);
                dtos.add(dto);
            }

            return dtos;
        } catch (Throwable e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "document.byId.CACHE", key = "#id")
    public DocumentDTO getDocumentById(Long id) {
        Validate.notNull(id);

        try {
            DLFileEntry documentEntity = dlFileEntryLocalService.getDLFileEntry(id);
            DocumentDTO dto = convertToDto(documentEntity);

            return dto;
        } catch (Throwable e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    @CacheEvict(value = "document.all.CACHE", allEntries = true)
    public long createDocument(DocumentDTO documentDTO, ServiceContext serviceContext) {
        Validate.notNull(documentDTO);
        Validate.notNull(serviceContext);
        Validate.isTrue(documentDTO.getId() == null);
        Validate.notNull(documentDTO.getFolderId());
        Validate.notNull(documentDTO.getRepositoryId());
        Validate.notNull(documentDTO.getSourceFileName());
        Validate.notEmpty(documentDTO.getSourceFileName());
        Validate.notNull(documentDTO.getUserId());
        Validate.notNull(documentDTO.getDescription());

        DLFileEntry entity = null;
        DocumentDTO dto = null;
        try {
            FileEntry fileEntry = dlAppLocalService.addFileEntry(documentDTO.getUserId(), documentDTO.getRepositoryId(), documentDTO.getFolderId(), documentDTO.getSourceFileName(),
                                                                 documentDTO.getMimeType(), documentDTO.getTitle(), documentDTO.getDescription(), documentDTO.getChangeLog(), documentDTO.getIs(),
                                                                 documentDTO.getSize(), serviceContext);
            return fileEntry.getFileEntryId();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "document.byId.CACHE", key = "#documentDTO.id"), @CacheEvict(value = "document.all.CACHE", allEntries = true)})
    public void updateDocument(DocumentDTO documentDTO, ServiceContext serviceContext) {
        Validate.notNull(serviceContext);
        Validate.notNull(documentDTO);
        Validate.notNull(documentDTO.getId());
        Validate.notNull(documentDTO.getSourceFileName());
        Validate.notEmpty(documentDTO.getSourceFileName());

        try {
            FileEntry fileEntry = DLAppLocalServiceUtil.updateFileEntry(documentDTO.getUserId(), documentDTO.getId(), documentDTO.getSourceFileName(), documentDTO.getMimeType(),
                                                                        documentDTO.getTitle(), documentDTO.getDescription(), documentDTO.getChangeLog(), true, documentDTO.getIs(),
                                                                        documentDTO.getSize(), serviceContext);
        } catch (PortalException | SystemException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "document.byId.CACHE", key = "#id"),
        @CacheEvict(value = "document.all.CACHE", allEntries = true)})
    public void deleteDocumentById(Long id) {
        Validate.notNull(id);

        DLFileEntry documentEntity = null;
        try {
            documentEntity = dlFileEntryLocalService.getDLFileEntry(id);
            dlFileEntryLocalService.deleteDLFileEntry(documentEntity);
        } catch (PortalException | SystemException e) {
            throw new ApplicationException(e);
        }
    }

    private DocumentDTO convertToDto(DLFileEntry documentEntity) throws SystemException, PortalException {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(documentEntity.getFileEntryId());
        dto.setFolderId(documentEntity.getFolderId());
        dto.setDescription(documentEntity.getDescription());
        dto.setIs(documentEntity.getContentStream());
        dto.setMimeType(documentEntity.getMimeType());
        dto.setSize(documentEntity.getSize());
        dto.setSourceFileName(documentEntity.getName());
        dto.setTitle(documentEntity.getTitle());
        dto.setRepositoryId(documentEntity.getRepositoryId());
        dto.setUserId(documentEntity.getUserId());

        return dto;
    }

}
