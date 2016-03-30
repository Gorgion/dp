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

import java.util.List;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cz.muni.fi.dp.core.entity.DummyEntity;
import cz.muni.fi.dp.core.repository.generic.GenericJpaRepository;

/**
 * Repository interface providing access to the data layer.
 */
@Repository
public interface DummyRepository extends GenericJpaRepository<DummyEntity, Long>, DummyRepositoryCustom {

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") }) // Use L2 cahce for Query
    DummyEntity findByName(String name);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") }) // Use L2 cahce for Query
    DummyEntity findByEmail(String email);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") }) // Use L2 cahce for Query
    @Query("FROM DummyEntity d WHERE d.email = :emailParam")
    List<DummyEntity> customQueryByEmail(@Param("emailParam") String email);

}
