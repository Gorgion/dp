/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package cz.muni.fi.dp.core.service;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import cz.muni.fi.dp.core.test.AbstractTest;
import cz.muni.fi.dp.core.test.JdbcTestUtils;
import cz.muni.fi.dp.iface.dto.DummyDto;
import cz.muni.fi.dp.iface.service.DummyService;

import static org.junit.Assert.*;

/**
 *
 */
public class DummyServiceImplTest extends AbstractTest {
    
    @Autowired
    private DummyService dummyService;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new ClassPathResource("sql/dummy.sql"), false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateDummy() {
        final String name = "Karel";
        final String email = "Karel@example.com";
        DummyDto dummyDto = new DummyDto(null, name, email);

        long id = dummyService.createDummy(dummyDto);
        DummyDto createdDummyDto = dummyService.getDummyById(id);
        
        assertTrue(createdDummyDto.getId() != null);
        assertEquals(name, createdDummyDto.getName());
    }
    
    @Test
    public void testGetDummyById() {
        DummyDto loadedDummyDto = dummyService.getDummyById(-1L);

        // test data created in dummy.sql - setUp() mathod
        assertEquals(Long.valueOf(-1L), loadedDummyDto.getId());
        assertEquals("Bedřich", loadedDummyDto.getName());
        assertEquals("bedrich@example.com", loadedDummyDto.getEmail());
    }
}
