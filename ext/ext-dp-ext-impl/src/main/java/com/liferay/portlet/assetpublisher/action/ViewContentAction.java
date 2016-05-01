/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package liferay.portlet.assetpublisher.action;

import javax.portlet.PortletConfig;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import liferay.portlet.assetpublisher.action.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import static liferay.portlet.assetpublisher.action.AssetPublisherConstants.PARAM_ASSET_ID;

/**
 * @author Julio Camarero
 */
public class ViewContentAction extends liferay.portlet.assetpublisher.action.ViewAction {

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		PortletSession session = renderRequest.getPortletSession();
		String assetId = (String) renderRequest.getAttribute(PARAM_ASSET_ID);
		if(session!= null && assetId != null && !assetId.isEmpty()) {
			session.setAttribute(AssetPublisherConstants.LIFERAY_SHARED_ASSET_ID, assetId, PortletSession.APPLICATION_SCOPE);
		}

		return actionMapping.findForward(
			"portlet.asset_publisher.view_content");
	}

}