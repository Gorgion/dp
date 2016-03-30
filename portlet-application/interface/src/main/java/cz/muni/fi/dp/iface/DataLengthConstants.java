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
package cz.muni.fi.dp.iface;

/**
 * Data lenght constants for back-end and front-end.
 */
public class DataLengthConstants {

    /** Max length of text in DB. Used for names (Event, Action, ...). */
    public static final int STRING_SHORT = 64;

    /** Max length of text in DB. Used for short descriptions. */
    public static final int STRING_NORMAL = 255;

    /** Max length of text in DB. Used for html comments etc. */
    public static final int STRING_LONG = 4096;

    /** Max length of text in DB. Used for long data. */
    public static final int STRING_HUGE = 1048576;

    /** Max length of text in DB. Used for huge text data. */
    public static final long STRING_UNLIMITED = 1073741824;

    /** Max size of price in DB. in db is used decimal(10,2) */
    public static final int MAX_PRICE = 9999999;
}
