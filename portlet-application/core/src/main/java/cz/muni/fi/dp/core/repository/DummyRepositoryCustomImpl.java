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

import eu.ibacz.commons.core.dao.JpaGenericDao;
import cz.muni.fi.dp.core.entity.DummyEntity;

/**
 * Repository implementation.
 *
 * This class has to be named [repository class name] + "CustomImpl" (see repository-impl-postfix in configuration)
 *
 */
public class DummyRepositoryCustomImpl extends JpaGenericDao<DummyEntity, Long> implements DummyRepositoryCustom {

    public DummyEntity someCustomMethod(Long id) {
        // return em.createNamedQuery("DummyEntity.someQuery").getSingleResult();
        return findByIdNullable(id);
    }

}
