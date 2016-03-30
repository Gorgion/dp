/*
 * ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2014 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================
 */

package cz.muni.fi.dp.web.portlet.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This is an example of standard unit test for class HelloViewControllerTest.
 * This test uses Mockito framework to mock its dependencies.
 *
 * @author Frantisek Hartman <frantisek.hartman@ibacz.eu>
 */
@RunWith(MockitoJUnitRunner.class)
public class HelloViewControllerTest {

    @Mock
    private cz.muni.fi.dp.iface.service.DummyService dummyService;

    @InjectMocks
    private HelloViewController controller;

    @Test
    public void doView() {
        /*
        // prepare handler parameters
        Model model = new ExtendedModelMap();
        RenderRequest request = new MockRenderRequest();
        RenderResponse response = new MockRenderResponse();

        // prepare mock
        List<DummyDto> dummyDTOs = Arrays.asList(new DummyDto());
        when(dummyService.getAllDummy()).thenReturn(dummyDTOs);

        // call tested controller
        String view = controller.doView(model, request, response);

        // verify results
        assertThat(view, is(VIEW_MAIN));
        assertThat(model, containsAttribute(ATTR_ALL_ITEMS));
        */
    }
}
