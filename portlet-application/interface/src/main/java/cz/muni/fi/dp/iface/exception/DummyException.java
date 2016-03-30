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
package cz.muni.fi.dp.iface.exception;

import eu.ibacz.commons.exception.ApplicationException;

/**
 * Exception indicating dummy action ;) .
 */
public class DummyException extends ApplicationException {

    /** Creates a new instance of the exception. */
    public DummyException() {
        super("Dummy Error.");
    }
}
