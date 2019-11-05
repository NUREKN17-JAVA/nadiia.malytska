package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.entity.SystemUser;

public class DAOFactoryImpl extends DAOFactory {

    @SuppressWarnings("unchecked")
    public DAO<SystemUser> getUserDao() {
        DAO<SystemUser> result = null;
        try {
            Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
            result = (DAO<SystemUser>) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}