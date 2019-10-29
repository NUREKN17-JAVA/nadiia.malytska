package ua.nure.malytska.usermanagement.db;

import ua.nure.malytska.usermanagement.SystemUser;

import java.sql.*;
import java.util.Collection;

public class HsqldbSystemUserDAO implements DAO<SystemUser> {

    private ConnectionFactory connectionFactory;
    private static final String INSERT_QUERY = "INSERT INTO system_user (first_name, last_name. date_of_birth) VALUES  (?,?,?)";
    private static final String CALL_IDENTITY = "call IDENTITY()";

    public HsqldbSystemUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public SystemUser create(SystemUser systemUser) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, systemUser.getFirstName());
            preparedStatement.setString(2, systemUser.getLastName());
            preparedStatement.setDate(3, new Date(systemUser.getDateOfBirth().getTime()));
            int numberOfRows = preparedStatement.executeUpdate();
            if(numberOfRows!=1) {
                throw new DatabaseException("Number of rows: " + numberOfRows);
            }
            CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
            ResultSet keys =  callableStatement.executeQuery();
            if(keys.next()){
                systemUser.setId(keys.getLong(1));
            }
            keys.close();
            callableStatement.close();
            preparedStatement.close();
            connection.close();
            return systemUser;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } catch (DatabaseException e){
            throw e;
        }
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
