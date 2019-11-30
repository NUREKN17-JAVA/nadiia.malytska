package ua.nure.malytska.usermanagement.db;

import com.mockobjects.dynamic.Mock;
import ua.nure.malytska.usermanagement.entity.SystemUser;

public class MockDAOFactory extends DAOFactory {
    private Mock mockUserDao;

    public MockDAOFactory() {
        mockUserDao = new Mock(DAO.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    @SuppressWarnings("unchecked")
    public DAO<SystemUser> getUserDao() {
        return (DAO<SystemUser>) mockUserDao.proxy();
    }

}
