/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package cz.muni.fi.dp.core.util;

import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

import eu.ibacz.commons.core.sqlupgrader.DbUpgrader;

/**
 * Zajišťuje automatické aktualizace aplikační DB.
 *
 * @author Ondřej Životský (ondrej.zivotsky@ibacz.eu)
 */
public class ApplicationDBUpgrader {

    private static final Logger LOG = Logger.getLogger(ApplicationDBUpgrader.class);

    public ApplicationDBUpgrader(DbUpgrader upgrader) {
        LogMF.info(LOG, "Application database upgrader: Current DB version is \"{0}\", current environment is \"{1}\".", new Object[]{upgrader.getCurrentVersion(), upgrader.getCurrentEnvironment()});

        upgrader.upgradeToNewest();
    }
}
