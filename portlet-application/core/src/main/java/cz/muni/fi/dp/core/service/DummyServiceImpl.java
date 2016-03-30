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
package cz.muni.fi.dp.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.dp.core.entity.DummyEntity;
import cz.muni.fi.dp.core.repository.DummyRepository;
import cz.muni.fi.dp.iface.dto.DummyDto;
import cz.muni.fi.dp.iface.service.DummyService;

/**
 * Service providing access to the data-access-layer.
 */
@Service
@Transactional
public class DummyServiceImpl implements DummyService {

    private static final Logger LOG = Logger.getLogger(DummyServiceImpl.class);

    @Autowired
    private DummyRepository dummyRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "dummy.all.CACHE")
    public List<DummyDto> getAllDummy() {
        LogMF.debug(LOG, "Getting AllDummyEntities", new Object[]{}); // getAll* metody by měly mít debug/trace logování

        LogMF.info(LOG, "Cache for method getAllDummy() is empty. Reading new data...", new Object[]{}); // jen pro demonstraci cache

        List<DummyEntity> entities = dummyRepository.findAll();

        List<DummyDto> dtos = new ArrayList<DummyDto>(entities.size());
        for (DummyEntity entity : entities) {
            DummyDto dto = convertToDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "dummy.byId.CACHE", key = "#id") // atribut "key" zde neni nutny, jde o priklad, jak vynutit vlastni klic
    public DummyDto getDummyById(Long id) {
        LogMF.debug(LOG, "Getting DummyEntity[id={0}]", new Object[]{String.valueOf(id)}); // get*By* metody by měly mít debug/trace logování

        LogMF.info(LOG, "Cache for method getDummyById({0}) is empty. Reading new data...", new Object[]{id}); // jen pro demonstraci cache

        Validate.notNull(id);

        DummyEntity dummyEntity = dummyRepository.findById(id);
        DummyDto dto = convertToDto(dummyEntity);

        return dto;
    }

    @Override
    @CacheEvict(value = "dummy.all.CACHE", allEntries = true)
    public long createDummy(DummyDto dummyDto) {
        LogMF.debug(LOG, "Trying to create {0}", new Object[]{dummyDto}); // create* metody by měly mít debug/trace logovani na začátku (co ukládám) a info na konci

        Validate.notNull(dummyDto);
        Validate.isTrue(dummyDto.getId() == null);

        DummyEntity entity = new DummyEntity();
        DummyDto dto = saveDummyEntity(dummyDto, entity);

        LogMF.info(LOG, "New item created: {0}", new Object[]{dto}); // create* metody by měly mít debug/trace logovani na začátku (co ukládám) a info na konci

        return dto.getId();
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "dummy.byId.CACHE", key = "#dummyDto.id"),
        @CacheEvict(value = "dummy.all.CACHE", allEntries = true)
    })
    public void updateDummy(DummyDto dummyDto) {
        LogMF.debug(LOG, "Trying to update {0}", new Object[]{dummyDto}); // update* metody by měly mít debug/trace logovani na začátku (co ukládám) a info na konci

        Validate.notNull(dummyDto);
        Validate.notNull(dummyDto.getId());

        DummyEntity dummyEntity = dummyRepository.findById(dummyDto.getId());
        DummyDto dto = saveDummyEntity(dummyDto, dummyEntity);

        LogMF.info(LOG, "Item updated: {0}", new Object[]{dto}); // update* metody by měly mít debug/trace logovani na začátku (co ukládám) a info na konci
    }

    private DummyDto saveDummyEntity(DummyDto dummyDto, DummyEntity entity) {
        entity.setName(dummyDto.getName());
        entity.setEmail(dummyDto.getEmail());
        DummyEntity savedDummyEntity = dummyRepository.save(entity);
        return convertToDto(savedDummyEntity);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "dummy.byId.CACHE", key = "#id"), // atribut "key" zde neni nutny, jde o priklad, jak vynutit vlastni klic
        @CacheEvict(value = "dummy.all.CACHE", allEntries = true)
    })
    public void deleteDummyById(Long id) {
        LogMF.info(LOG, "Deleting DummyEntity[id={0}]", new Object[]{id}); // delete* metody by měly mít info log (ideálně nejen ID, ale i podrobnější data)

        Validate.notNull(id);

        DummyEntity dummyEntity = dummyRepository.findById(id);
        dummyRepository.delete(dummyEntity);
    }

    private DummyDto convertToDto(DummyEntity dummyEntity) {
        DummyDto dto = new DummyDto();
        dto.setId(dummyEntity.getId());
        dto.setName(dummyEntity.getName());
        dto.setEmail(dummyEntity.getEmail());
        return dto;
    }
}
