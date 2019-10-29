package ua.nure.malytska.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import ua.nure.malytska.usermanagement.SystemUser;

import java.util.Date;

public class HsqldbSystemUserDAOTest extends DatabaseTestCase {

    private HsqldbSystemUserDAO dao;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private ConnectionFactory connectionFactory;

    public void testCreate() throws DatabaseException {
        SystemUser systemUser = new SystemUser();
        systemUser.setFirstName(FIRST_NAME);
        systemUser.setLastName(LAST_NAME);
        systemUser.setDateOfBirth(new Date());
        assertNull(systemUser.getId());
        SystemUser systemUserToCheck = dao.create(systemUser);
        assertNotNull(systemUserToCheck);
        assertNotNull(systemUserToCheck.getId());
    }

    public void setUp() throws Exception {
        super.setUp();
        dao = new HsqldbSystemUserDAO(connectionFactory);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl();
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }
}