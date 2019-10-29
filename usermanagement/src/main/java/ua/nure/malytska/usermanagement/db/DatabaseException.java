package ua.nure.malytska.usermanagement.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(Exception e) {
        super(e);
    }
}
