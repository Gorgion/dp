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
package cz.muni.fi.dp.core.repository.generic;

import java.io.Serializable;

import eu.ibacz.commons.core.entity.BaseEntity;

/**
 * Custom methods for all repositories.
*/
@SuppressWarnings("UnusedDeclaration")
public interface CustomJpaRepository<T extends BaseEntity<ID>, ID extends Serializable> {

    T findById(ID id);
    T findByIdNullable(ID id);

}
