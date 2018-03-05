package demo;


import dao.HotelDAO;
import dao.RoomDAO;
import entity.Hotel;
import entity.Room;

import java.util.Date;

public class HotelDemo {
    public static void main(String[] args) throws Exception {

        Hotel hotel = new Hotel("HH","jj","jj","dfd");
        HotelDAO hotelDAO = new HotelDAO();
//        System.out.println(hotelDAO.add(hotel));

        Room room = new Room(1,111,true,true,new Date(),hotel);
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.add(room);

    }
}
