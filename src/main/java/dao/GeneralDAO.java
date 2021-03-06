package dao;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public abstract class GeneralDAO<T> {


    private static final String DB_URL = "jdbc:oracle:thin:@jdbctest.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "admin";
    private static final String PASS = "helloworld";
    private String dbName;


    public ArrayList<T> getAll() throws Exception {

        ArrayList<T> listOfObjects;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + dbName + "")) {
            ResultSet result = statement.executeQuery();
            listOfObjects = createListObjectsFromDB(connection, result);
        } catch (SQLException e) {
            throw new Exception("Cant get items fromDB with name " + dbName);
        }
        return listOfObjects;
    }

    public void delete(long id) throws Exception {


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM " + dbName + " WHERE ID = ?")) {
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Cant delete object with id  " + id + " from DB with name  " + dbName);

        }
    }

    public T getObjectById(long id) throws Exception {
        T t = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + dbName + " WHERE ID = ?")) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next())
                t = createObjectFromDB(connection, result);
        } catch (SQLException e) {
            throw new Exception("Cant find object with id " + id + " in DB with name " + dbName);
        }
        return t;
    }

    public T getObjectById(Connection connection, long id) throws Exception {
        T t = null;

        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + dbName + " WHERE ID = ?")) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next())
                t = createObjectFromDB(connection, result);
        } catch (SQLException e) {
            throw new Exception("Cant find object with id " + id + " in DB with name " + dbName);
        }
        return t;
    }

    abstract T createObjectFromDB(Connection connection, ResultSet result) throws Exception;

    abstract ArrayList<T> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}