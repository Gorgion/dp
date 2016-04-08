package cz.muni.fi.dp.iface.common;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by Tomas on 08.04.2016.
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(Throwable e) {
        super(e);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
