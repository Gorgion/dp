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
package cz.muni.fi.dp.core.entity;

import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import eu.ibacz.commons.core.entity.BaseEntity;
import cz.muni.fi.dp.iface.DataLengthConstants;

/**
 * Dummy entity.
 */
@Entity
@Cacheable // Use L2 cahce for entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name="dummy")
public class DummyEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="dummy_name", nullable=false, length=DataLengthConstants.STRING_SHORT)
    private String name;

    @Column(name="dummy_email", nullable=true, length=DataLengthConstants.STRING_NORMAL)
    private String email;

    //@Column(name="dummy_start", nullable=false)
    //@Temporal(TemporalType.TIMESTAMP)
    //private Date start;

    //@Column(name="dummy_type", nullable=false)
    //@Enumerated(EnumType.STRING)
    //private String type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
