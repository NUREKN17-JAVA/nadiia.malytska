package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.entity.SystemUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDAO implements DAO<SystemUser> {
    private long id = 0;
    private Map<Long, SystemUser> users = new HashMap<>();

    public SystemUser create(SystemUser user) throws DatabaseException {
        System.out.println("create " + user);
        Long currentId = ++id;
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    public void update(SystemUser user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
    }

    public void delete(SystemUser user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

    public SystemUser find(Long id) throws DatabaseException {
        return users.get(id);
    }

    public Collection<SystemUser> findAll() throws DatabaseException {
        return users.values();
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
    }

    public Collection<SystemUser> find(String firstName, String lastName)
            throws DatabaseException {
        throw new UnsupportedOperationException();
    }
}
