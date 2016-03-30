/**
 *  ===========================================================================
 *  IBA CZ Confidential
 *
 *  © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *  The source code for this program is not published or otherwise
 *  divested of its trade secrets.
 *  ===========================================================================
 *
 */
package cz.muni.fi.dp.web.portlet.hello;

import cz.muni.fi.dp.web.portlet.Constants;

/**
 * This class contains shared constants for controllers and JSP pages
 */
public class HelloConstants extends Constants {

    public static final String VIEW_MAIN = "hello/view";
    public static final String VIEW_CREATE_FORM = "hello/view_create";
    public static final String VIEW_EDIT_FORM = "hello/view_edit";
    public static final String VIEW_DETAIL = "hello/view_detail";


    public static final String PAGE_DETAIL = "detail";
    public static final String PAGE_CREATE_FORM = "addForm";
    public static final String PAGE_EDIT_FORM = "editForm";


    public static final String ACTION_CREATE = "createAction";
    public static final String ACTION_UPDATE = "updateAction";
    public static final String ACTION_DELETE = "deleteAction";
    public static final String ACTION_ADD_EMAIL = "addEmailAction";


    public static final String RESOURCE_AUTOCOMPLETE = "autocomplete";


    public static final String ATTR_ALL_ITEMS = "allItems";
    public static final String ATTR_ID = "itemId";
    public static final String ATTR_DUMMY_PTO = "dummyPto";
    public static final String ATTR_DUMMY_DTO = "dummyDto";
    public static final String ATTR_ADD_EMAIL = "addEmail";
    public static final String ATTR_DELETE_PERMISSION = "deletePermission";

    public static final String PARAM_ID = "entityId";
    public static final String PARAM_ADD_EMAIL = "addEmail";
}
