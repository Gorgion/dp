package cz.muni.fi.dp.iface.dto;

import java.io.InputStream;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for document entity.
 */
@Data
@NoArgsConstructor
public class DocumentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Long repositoryId;
    private Long folderId;
    private String sourceFileName;
    private String mimeType;
    private String title;
    private String description;
    private String changeLog;
    private InputStream is;
    private Long size;
}
