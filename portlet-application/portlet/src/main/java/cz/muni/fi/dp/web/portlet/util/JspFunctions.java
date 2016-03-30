/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package cz.muni.fi.dp.web.portlet.util;

import com.liferay.portal.kernel.util.UnicodeFormatter;
import org.apache.log4j.Logger;

/**
 * JSP funkce.
 * @author Ondřej Životský (ondrej.zivotsky@ibacz.eu)
 */
public class JspFunctions {

    private static final Logger LOG = Logger.getLogger(JspFunctions.class);

    
    /**
     * Returns string as unicode formatted text.
     * @param str plain text
     * @return unicode text
     */
    public static String getAsUnicodeText(String str) {
        return UnicodeFormatter.toString(str);
    }
}
