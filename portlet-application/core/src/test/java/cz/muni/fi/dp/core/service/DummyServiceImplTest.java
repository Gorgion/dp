/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package cz.muni.fi.dp.core.service;

import cz.muni.fi.dp.iface.dto.DocumentDTO;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import cz.muni.fi.dp.core.test.AbstractTest;
import cz.muni.fi.dp.core.test.JdbcTestUtils;
import cz.muni.fi.dp.iface.service.DocumentService;

import static org.junit.Assert.*;

/**
 *
 */
public class DummyServiceImplTest extends AbstractTest {
    
    @Autowired
    private DocumentService dummyService;
    
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
        DocumentDTO documentDTO = new DocumentDTO(null, name, email);

        long id = dummyService.createDummy(documentDTO);
        DocumentDTO createdDocumentDTO = dummyService.getDummyById(id);
        
        assertTrue(createdDocumentDTO.getId() != null);
        assertEquals(name, createdDocumentDTO.getName());
    }
    
    @Test
    public void testGetDummyById() {
        DocumentDTO loadedDocumentDTO = dummyService.getDummyById(-1L);

        // test data created in dummy.sql - setUp() mathod
        assertEquals(Long.valueOf(-1L), loadedDocumentDTO.getId());
        assertEquals("Bedřich", loadedDocumentDTO.getName());
        assertEquals("bedrich@example.com", loadedDocumentDTO.getEmail());
    }
}
