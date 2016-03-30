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
package cz.muni.fi.dp.core.repository;

import cz.muni.fi.dp.core.entity.DummyEntity;

/**
 * Repository interface providing access to the data layer.
 */
public interface DummyRepositoryCustom {

    DummyEntity someCustomMethod(Long id);

}
