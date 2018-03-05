package demo;


import dao.OrderDAO;
import service.OrderService;

import java.util.Date;

public class OrderDemo {
    public static void main(String[] args) throws Exception {

        OrderService orderService = new OrderService();
//        orderService.bookRoom(2555,648,new Date());
        orderService.cancelReservation(2555,648);
    }
}
