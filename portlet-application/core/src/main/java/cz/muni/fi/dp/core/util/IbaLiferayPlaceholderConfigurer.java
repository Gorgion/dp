/*
 * ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 * ===========================================================================
 */

package cz.muni.fi.dp.core.util;

import java.util.Properties;

import com.liferay.portal.kernel.util.PropsUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Přednostně načítá property z Liferaye (portal.properties).
 * @author Libor Subcik (libor.subcik@ibacz.eu)
 */
public class IbaLiferayPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String DEFAULT_LIFERAY_PREFIX = "%{";

    private boolean requiredInLiferay = true;

    public IbaLiferayPlaceholderConfigurer() {
        setPlaceholderPrefix(DEFAULT_LIFERAY_PREFIX);
    }

    public void setRequiredInLiferay(boolean requiredInLiferay) {
        this.requiredInLiferay = requiredInLiferay;
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        String result = getPropertyFromLiferay(placeholder);
        if (result != null) {
            return result;
        }

        if (requiredInLiferay) {
            throw new IllegalStateException("Property " + placeholder + " cannot be found in Liferay.");
        } else {
            return super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
        }
    }

    private String getPropertyFromLiferay(String placeholder) {
        return PropsUtil.get(placeholder);
    }

}
