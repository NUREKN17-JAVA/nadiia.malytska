package ua.nure.malytska.usermanagement.web;

import org.junit.Before;
import org.junit.Test;
import ua.nure.malytska.usermanagement.entity.SystemUser;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase{
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    @Test
    public void testBrowse() {
        SystemUser user = new SystemUser(1000L, "John", "Doe", new Date());
        List<SystemUser> list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        @SuppressWarnings("unchecked")
        Collection<SystemUser> attrUsers = (Collection<SystemUser>) getWebMockObjectFactory()
                .getMockSession().getAttribute("users");
        assertNotNull(attrUsers);
        assertSame(list, attrUsers);
    }

    @Test
    public void testEdit() {
        SystemUser user = new SystemUser(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        SystemUser userInSession = (SystemUser) getWebMockObjectFactory()
                .getMockSession().getAttribute("user");
        assertNotNull(userInSession);
        assertSame(user, userInSession);
    }

    @Test
    public void testEditWithoutId() {
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    @Test
    public void testDetails() {
        SystemUser user = new SystemUser(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("detailsButton", "Details");
        addRequestParameter("id", "1000");
        doPost();
        SystemUser userInSession = (SystemUser) getWebMockObjectFactory()
                .getMockSession().getAttribute("user");
        assertNotNull(userInSession);
        assertSame(user, userInSession);
    }

    @Test
    public void testDetailsWithoutId() {
        addRequestParameter("detailsButton", "Details");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    @Test
    public void testDelete() {
        SystemUser user = new SystemUser(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        getMockUserDao().expect("delete", user);
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("message"));
    }

    @Test
    public void testDeleteWithoutId() {
        addRequestParameter("deleteButton", "Delete");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
}