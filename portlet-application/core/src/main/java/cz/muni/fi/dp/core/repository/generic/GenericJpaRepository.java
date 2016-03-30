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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import eu.ibacz.commons.core.entity.BaseEntity;

/**
 * Base extendable interface for all repositories.
 */
@NoRepositoryBean
public interface GenericJpaRepository<T extends BaseEntity<ID>, ID extends Serializable> extends CustomJpaRepository<T, ID>, JpaRepository<T, ID> {

}
