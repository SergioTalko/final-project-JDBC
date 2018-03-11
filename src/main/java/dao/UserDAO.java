package dao;

import entity.User;
import entity.UserType;
import exceptions.BadRequestException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends GeneralDAO<User> {

    public UserDAO() {
        super.setDbName("USERS");
    }


    public User add(User user) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?)")) {

            if (getUserByName(connection, user.getUserName()) != null)
                throw new BadRequestException("User with name " + user.getUserName() + " already exists");
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getCountry());
            statement.setString(5, user.getUserType().name());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new Exception("Some problem with saving user with name" + user.getUserName());
        }
        return user;
    }

    public User findUserByName(String name) throws Exception {
        if (name == null) throw new NullPointerException("Input data is null");

        ArrayList<User> users = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement hotelStatement = connection.prepareStatement("SELECT * FROM  USERS WHERE USER_NAME = ?")) {

            hotelStatement.setString(1, name);
            ResultSet result = hotelStatement.executeQuery();
            while (result.next())
                users.add(createObjectFromDB(connection, result));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Cant find object with name " + name + "in DB with name HOTELS");

        }

        if (users.size() != 1) throw new Exception("You have more than 1 user with same name -- " + name);
        return users.get(0);
    }

    @Override
    User createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        long userId = result.getLong(1);
        String name = result.getString(2);
        String password = result.getString(3);
        String country = result.getString(4);
        UserType userType = UserType.valueOf(result.getString(5));
        User user = new User(name, password, country,userType);
        user.setId(userId);
        return user;
    }

    @Override
    ArrayList<User> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        ArrayList<User> users = new ArrayList<>();
        while (result.next()) {
            users.add(createObjectFromDB(connection, result));
        }
        return users;
    }

    public User getUserByName(Connection connection, String name) throws Exception {

        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ?")) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next())
                user = createObjectFromDB(connection, result);
        } catch (SQLException e) {
            throw new Exception("Cant find user with name " + name + " in DB with name USERS ");
        }
        return user;
    }

}
