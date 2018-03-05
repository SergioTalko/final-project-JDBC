package dao;

import entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAO extends GeneralDAO<Order> {

    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public OrderDAO() {
        super.setDbName("ORDERS");
    }


    public Order add(Order order) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setLong(1, order.getId());
            statement.setLong(2, order.getUser().getId());
            statement.setLong(3, order.getRoom().getId());
            statement.setDate(4, new java.sql.Date(order.getDateFrom().getTime()));
            statement.setDate(5, new java.sql.Date(order.getDateTo().getTime()));
            statement.setDouble(6, order.getMoneyPaid());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new Exception("Cant save order with id " + order.getId());
        }
        return order;
    }

    public Order update(Order order) throws Exception {

        if (order == null) throw new NullPointerException("Cant save null");
        if (getObjectById(order.getId()) == null) throw new Exception("Cant find order with id " + order.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE ORDERS SET  USER_ID = ?,ROOM_ID = ?,DATE_FROM = ?, DATE_TO = ?, MONEY_PAID = ?,  WHERE ID = ?")) {


            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getRoom().getId());
            statement.setDate(3, new java.sql.Date(order.getDateFrom().getTime()));
            statement.setDate(4, new java.sql.Date(order.getDateFrom().getTime()));
            statement.setDouble(5, order.getMoneyPaid());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update order with id " + order.getId() + " try again later.");
            e.printStackTrace();
        }
        return order;
    }

    @Override
    Order createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        long orderId = result.getLong(1);
        long userId = result.getLong(2);
        long roomId = result.getLong(3);
        Date dateFrom = new Date(result.getDate(4).getTime());
        Date dateTo = new Date(result.getDate(5).getTime());
        double moneyPaid = result.getDouble(6);
        Order order = new Order(userDAO.getObjectById(userId), roomDAO.getObjectById(roomId), dateFrom, dateTo, moneyPaid);
        order.setId(orderId);
        return order;
    }

    @Override
    public ArrayList<Order> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        while (result.next()) {
            orders.add(createObjectFromDB(connection, result));
        }
        return orders;

    }

}
