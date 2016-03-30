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
package cz.muni.fi.dp.web.portlet.hello.pto;

import cz.muni.fi.dp.iface.DataLengthConstants;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * PTO for Dummy DTO.
 */
public class DummyPto {
    private Long id;

    @NotEmpty
    @Size(max=DataLengthConstants.STRING_SHORT)
    private String name;

    @Email
    @Size(max=DataLengthConstants.STRING_SHORT)
    private String email;

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
