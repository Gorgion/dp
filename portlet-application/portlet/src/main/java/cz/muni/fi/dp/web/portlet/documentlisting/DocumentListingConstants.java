package cz.muni.fi.dp.web.portlet.documentlisting;

import cz.muni.fi.dp.web.portlet.Constants;

/**
 * This class contains shared constants for controllers and JSP pages
 */
public class DocumentListingConstants extends Constants {

    public static final String VIEW_MAIN = "documentlisting/view";
    public static final String VIEW_DETAIL = "documentlisting/view_detail";
    public static final String VIEW_UPLOAD = "documentlisting/view_upload";
    public static final String VIEW_EDIT = "documentlisting/view_edit";

    public static final String PAGE_DETAIL = "detail";
    public static final String PAGE_UPLOAD = "upload";
    public static final String PAGE_EDIT = "edit";

    public static final String ACTION_UPLOAD = "uploadAction";
    public static final String ACTION_DELETE = "deleteAction";
    public static final String ACTION_UPDATE = "updateAction";

    public static final String ATTR_ALL_DOCUMENTS = "allItems";
    public static final String ATTR_DOCUMENT_DTO = "documentDto";
    public static final String ATTR_DOCUMENT_PTO = "documentPto";
    public static final String ATTR_DOWNLOAD_URL = "downloadUrl";

    public static final String PARAM_ID = "entityId";
    public static final String PARAM_DOCUMENT_PTO = "documentPto";

}
