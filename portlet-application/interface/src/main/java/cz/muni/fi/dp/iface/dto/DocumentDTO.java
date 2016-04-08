package cz.muni.fi.dp.iface.dto;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Data Transfer Object for document entity.
 */
public class DocumentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long repositoryId;
    private Long folderId;
    private String sourceFileName;
    private String mimeType;
    private String title;
    private String description;
    private String changeLog;
    private InputStream is;
    private Long size;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, Long repositoryId, Long folderId, String sourceFileName, String mimeType, String title,
                       String description, String changeLog, InputStream is, Long size) {
        this.id = id;
        this.repositoryId = repositoryId;
        this.folderId = folderId;
        this.sourceFileName = sourceFileName;
        this.mimeType = mimeType;
        this.title = title;
        this.description = description;
        this.changeLog = changeLog;
        this.is = is;
        this.size = size;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public DocumentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public DocumentDTO setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    public Long getFolderId() {
        return folderId;
    }

    public DocumentDTO setFolderId(Long folderId) {
        this.folderId = folderId;
        return this;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public DocumentDTO setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public DocumentDTO setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DocumentDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocumentDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public DocumentDTO setChangeLog(String changeLog) {
        this.changeLog = changeLog;
        return this;
    }

    public InputStream getIs() {
        return is;
    }

    public DocumentDTO setIs(InputStream is) {
        this.is = is;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public DocumentDTO setSize(Long size) {
        this.size = size;
        return this;
    }
}
