package ua.nure.malytska.usermanagement;

import junit.framework.TestCase;
import ua.nure.malytska.usermanagement.db.DAO;
import ua.nure.malytska.usermanagement.db.DAOFactory;
import ua.nure.malytska.usermanagement.entity.SystemUser;

public class DAOFactoryTest extends TestCase {

    public void testGetUserDAO(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertNotNull("DAOFactory instance is null", daoFactory);
        DAO<SystemUser> result = daoFactory.getUserDao();
        assertNotNull("UserDao instance is null", result);
    }
}
