package controller;


import dao.OrderDAO;
import dao.RoomDAO;
import dao.UserDAO;
import entity.User;
import entity.Room;

import java.util.Date;

public class OrderController {

    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId) throws Exception {

    }

    public void cancelReservation(long roomId, long userId) throws Exception {

    }
}
