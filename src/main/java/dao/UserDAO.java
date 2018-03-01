package dao;

import entity.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO extends GeneralDAO<User> {






    @Override
    User add(User user) throws Exception {
        return null;
    }

    @Override
    User createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }

    @Override
    ArrayList<User> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }
}
