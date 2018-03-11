package service;

import dao.OrderDAO;
import dao.RoomDAO;
import dao.UserDAO;
import entity.Order;
import entity.Room;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId, Date dateTo) throws Exception {

        try (Connection connection = orderDAO.getConnection()) {

            connection.setAutoCommit(false);
            User user = userDAO.getObjectById(connection, userId);
            Room room = roomDAO.getObjectById(connection, roomId);

            long difference = dateTo.getTime() - room.getDateAvailableFrom().getTime();
            long days = difference / (24 * 60 * 60 * 1000);
            double moneyPaid = ((room.getPrice()) * days);

            orderDAO.add(connection, new Order(user, room, room.getDateAvailableFrom(), dateTo, moneyPaid));
            room.setDateAvailableFrom(dateTo);

            roomDAO.update(connection, room);

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Cant create order.Please try again later");
            e.printStackTrace();
        }

    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        if (roomId <= 0 || userId <= 0) throw new Exception("Check input data");


        try (Connection connection = orderDAO.getConnection()) {

            connection.setAutoCommit(false);
            Order order = orderDAO.getOrderByRoomIdAndUserId(connection, roomId, userId);
            Room room = roomDAO.getObjectById(connection, roomId);

            room.setDateAvailableFrom(order.getDateFrom());
            roomDAO.update(connection, room);

            orderDAO.deleteOrder(connection, roomId, userId);

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Cant cancel order.Please try again later");
        }

    }
}



