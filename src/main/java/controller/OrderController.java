package controller;

import service.OrderService;

import java.nio.file.AccessDeniedException;
import java.util.Date;

public class OrderController {

    OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateTo) throws Exception {
        if (Session.getUserInSession() != null) {
            orderService.bookRoom(roomId, userId, dateTo);
        }
        throw new AccessDeniedException("Please login in system");
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        if (Session.getUserInSession() != null) {
            orderService.cancelReservation(roomId, userId);
        }
        throw new AccessDeniedException("Please login in system");
    }
}

