package service;

import dao.OrderDAO;
import dao.RoomDAO;
import dao.UserDAO;
import entity.Order;
import entity.Room;
import entity.User;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId,Date dateTo) throws Exception {

        User user = userDAO.getObjectById(userId);
        Room room = roomDAO.getObjectById(roomId);
        long difference = dateTo.getTime()  - room.getDateAvailableFrom().getTime();
        long days = difference/(24*60*60*1000);
        double moneyPaid = ((room.getPrice()) * days);

        orderDAO.add(new Order(user, room, room.getDateAvailableFrom(),dateTo,moneyPaid));

        room.setDateAvailableFrom(dateTo);

        roomDAO.update(room);

    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        Room roomForCancel = roomDAO.getObjectById(roomId);

        for (Order order : orderDAO.getAll()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) {
                roomForCancel.setDateAvailableFrom(order.getDateFrom());
                orderDAO.delete(order.getId());
                roomDAO.update(roomForCancel);
                break;
            }


        }

    }
}
