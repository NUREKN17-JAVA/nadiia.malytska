package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.SystemUser;

import java.util.Collection;

public class HsqldbSystemUserDAO implements DAO<SystemUser> {
    @Override
    public SystemUser create(SystemUser systemUser) throws DatabaseException {
        return null;
    }

    @Override
    public void update(SystemUser systemUser) throws DatabaseException {

    }

    @Override
    public void delete(SystemUser systemUser) throws DatabaseException {

    }

    @Override
    public SystemUser find(Long id) throws DatabaseException {
        return null;
    }

    @Override
    public Collection<SystemUser> findAll() throws DatabaseException {
        return null;
    }
}
