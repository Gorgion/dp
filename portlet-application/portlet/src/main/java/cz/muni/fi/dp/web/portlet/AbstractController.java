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
/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * ALL LiferayUtils DEPENDEND CODE IS IN COMMENTS
 * 
 * If you wish to use LiferayUtils uncomment it
 * and also uncoment (and alter) the dependency in pom.xml
 * 
 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

package cz.muni.fi.dp.web.portlet;

/*
import eu.ibacz.commons.liferay.facade.UserServiceFacade;
import eu.ibacz.commons.liferay.model.LiferayUser;
import eu.ibacz.commons.liferay.util.LiferayUtils;
 */

import eu.ibacz.commons.portlet.BaseController;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import java.util.Locale;


/**
 * This class is base controller for application.
 */
public class AbstractController extends BaseController {

    /**
     * UserServiceFacade is Liferay version specific
     */
//    @Autowired
//    protected UserServiceFacade userServiceFacade;


/*
    @Override
    public LiferayUser getCurrentUser(PortletRequest request) {
        try {
            return userServiceFacade.getUser(getCurrentUserId(request));
        } catch (Exception e) {
            return null;
        }
    }
*/

    @Override
    public String getCurrentUser(PortletRequest request) {
        return request.getRemoteUser();
    }

    @Override
    public long getCompanyId(PortletRequest request) {
//        return LiferayUtils.getCompanyId(request);

        return 0;
    }

    @Override
    public Locale getCurrentLocale(RenderRequest request) {
//        return LiferayUtils.getLocale(request);

        return request.getLocale();
    }
}
