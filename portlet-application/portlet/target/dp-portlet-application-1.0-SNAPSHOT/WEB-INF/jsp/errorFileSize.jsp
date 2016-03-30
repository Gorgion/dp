<%-- =======================================================================
 *   IBA CZ Confidential
 *
 *    © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 *    The source code for this program is not published or otherwise
 *    divested of its trade secrets.
 *
 * =============================================================================================

JSP pro výpis chyby v případě překročení limitu pro upload.

Parametry:
    MaxUploadSizeExceededException exception      - Objekt vyjímky.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="init.jspf"%>

<%@page import="java.text.SimpleDateFormat"%>


<c:set var="exeptionObj" value="${exception}" />
<c:set var="maxFileSize" value="${exception.maxUploadSize}" />
<c:set var="currentFileSize" value="${exception.cause.actualSize}" /> <%-- exception.getCause() instanceof FileUploadBase.SizeLimitExceededException --%>


<div class="portlet-msg-alert">
    <spring:message code='msg-common-upload-max-size-info' arguments="${iba:escapeHtml(iba:formatFileSize(maxFileSize, 2, locale))}" />
</div>

<div class="portlet-msg-error">
    <spring:message code='msg-common-err-upload' />
</div>

<c:if test="${not empty currentFileSize}">
    <div class="portlet-msg-info">
        <spring:message code='msg-common-upload-current-size-info' argumentSeparator="***"
                        arguments="${iba:formatFileSize(currentFileSize, 2, locale)}***${iba:formatFileSize(currentFileSize-maxFileSize, 2, locale)}" />
    </div>
</c:if>

<c:if test="${not empty exeptionObj}">
    <div style="display: none;">
        <div>
            <%= java.text.SimpleDateFormat.getDateTimeInstance(
                    java.text.SimpleDateFormat.MEDIUM,
                    java.text.SimpleDateFormat.MEDIUM,
                    locale
                ).format(new java.util.Date())
            %>
        </div>
        ${exeptionObj}
    </div>
</c:if>

<div class="buttons">

    <input type="button" class="button back-button"
           onclick="javascript:history.go(-1);"
           value="<spring:message code='msg-common-btn-back'/>"
           title="<spring:message code='msg-common-upload-err-back-btn-title'/>" />
</div>
