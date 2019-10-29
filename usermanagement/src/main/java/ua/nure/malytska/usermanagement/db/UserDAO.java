package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.SystemUser;

import java.util.Collection;

public interface UserDAO {
    SystemUser create(SystemUser entity) throws DatabaseException;

    void update(SystemUser systemUser) throws DatabaseException;

    void delete(SystemUser systemUser) throws DatabaseException;

    SystemUser find(Long id) throws DatabaseException;

    Collection<SystemUser> findAll() throws DatabaseException;
}
