/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package cz.muni.fi.dp.core.repository;

import java.util.List;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import cz.muni.fi.dp.core.entity.DummyEntity;
import cz.muni.fi.dp.core.test.AbstractTest;
import cz.muni.fi.dp.core.test.JdbcTestUtils;

import static org.junit.Assert.*;

/**
 *
 */
public class DummyRepositoryTest extends AbstractTest {
    
    @Autowired
    private DummyRepository dummyRepository;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCustomRepositoryMethod() {
        final String name = "Karel";
        final String email = "Karel@example.com";

        DummyEntity entity = new DummyEntity();
        entity.setName(name);
        entity.setEmail(email);

        Long id = dummyRepository.save(entity).getId();

        // Custom global-repo method
        assertNull(dummyRepository.findByIdNullable(1234567L));

        // Custom repo method
        DummyEntity newOne = dummyRepository.someCustomMethod(id);
        assertEquals(entity, newOne);
        assertEquals(entity.getName(), newOne.getName());

        // Cusrom SQL method
        List<DummyEntity> res = dummyRepository.customQueryByEmail(email);
        assertTrue(res.size() == 1);
        assertEquals(res.get(0), entity);
    }
}
