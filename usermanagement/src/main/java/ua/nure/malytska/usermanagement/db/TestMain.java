package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.entity.SystemUser;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        SystemUser systemUser = new SystemUser();
        systemUser.setFirstName("Nadechka");
        systemUser.setLastName("Malitskaya");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 9);
        systemUser.setDateOfBirth(calendar.getTime());
        DAO<SystemUser> systemUserDAO = DAOFactory.getInstance().getUserDao();
        try {
            SystemUser systemUser1 = systemUserDAO.create(systemUser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }


}
