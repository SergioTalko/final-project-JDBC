package dao;
import entity.Order;
import exceptions.FormatDataInDatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderDAO extends GeneralDAO<Order> {


    @Override
    Order add(Order order) throws Exception {
        return null;
    }

    @Override
    Order createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }

    @Override
    ArrayList<Order> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }
}
