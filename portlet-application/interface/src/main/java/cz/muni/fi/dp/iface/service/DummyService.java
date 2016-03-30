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
package cz.muni.fi.dp.iface.service;

import java.util.List;

import cz.muni.fi.dp.iface.dto.DummyDto;

/**
 * Service providing access to the data-access-layer.
 */
public interface DummyService {

    List<DummyDto> getAllDummy();

    DummyDto getDummyById(Long id);

    long createDummy(DummyDto dummyDto);

    void updateDummy(DummyDto dummyDto);

    void deleteDummyById(Long id);
}
