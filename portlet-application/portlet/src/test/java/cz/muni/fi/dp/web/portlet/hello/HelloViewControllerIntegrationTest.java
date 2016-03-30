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

import java.io.IOException;
import javax.naming.NamingException;
import javax.portlet.PortletException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.ibacz.commons.test.PortletIntegrationTest;
import eu.ibacz.commons.test.PortletItegrationTestConfiguration;
import cz.muni.fi.dp.iface.service.DummyService;

/**
 * This is an example of integration test of spring framework and portlet controllers
 * All dependencies (service beans) are defined in mock-applicationContext.xml file.
 * You can also provide real implementation with in memory database.
 *
 * @author Frantisek Hartman <frantisek.hartman@ibacz.eu>
 */
public class HelloViewControllerIntegrationTest extends PortletIntegrationTest {

    public static final String COMMON_CONTEXT_XML
            = "common-context.xml";
    public static final String HELLO_PORTLET_CONTEXT_XML
            = "file:src/main/webapp/WEB-INF/spring-context/portlet/hello-portlet.xml";
    public static final String MOCK_APPLICATION_CONTEXT_XML
            = "mock-applicationContext.xml";

    private DummyService dummyService;
    private HelloViewController controller;

    @BeforeClass
    public static void setup() throws PortletException, NamingException {
        PortletItegrationTestConfiguration configuration = new PortletItegrationTestConfiguration();

        configuration.setAppContextConfigLocations(MOCK_APPLICATION_CONTEXT_XML);
        configuration.setPortletContextConfigLocations(
                COMMON_CONTEXT_XML,
                HELLO_PORTLET_CONTEXT_XML);

        initContext(configuration);
    }

    @Before
    public void setUp() {
        dummyService = getBean(DummyService.class);
        controller = getBean(HelloViewController.class);
    }


    @Test
    public void detail() throws PortletException, IOException {
        /*
        // prepare request
        renderRequest.setParameter(PARAM_PAGE, PAGE_DETAIL);
        renderRequest.setParameter(PARAM_ID, "0");

        // prepare mock
        when(dummyService.getDummyById(0L)).thenReturn(new DummyDto());

        // call dispatcher portlet - this will call controller
        // it will process all annotations to determine which method
        dispatcherPortlet.render(renderRequest, renderResponse);

        // check view returned by controller
        String view = getReturnedView(renderRequest);
        assertThat(view, is(VIEW_DETAIL));

        // check that model contains required attribute
        Model model = getModelFromContentRequest(renderRequest);
        assertThat(model, containsAttribute(ATTR_DUMMY_PTO));

        // verify that a handler method was called
        // not necessary in most cases where the handler returns something (see documentation of verify method)
        verify(controller).detail(anyLong(), any(Model.class));
        */
    }
}
