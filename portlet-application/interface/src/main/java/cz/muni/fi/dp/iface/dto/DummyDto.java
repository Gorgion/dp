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
package cz.muni.fi.dp.iface.dto;

import eu.ibacz.commons.dto.BaseDTO;

/**
 * Data Transfer Object for Dummy entity.
 */
public class DummyDto extends BaseDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    public DummyDto() {
    }

    public DummyDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
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
