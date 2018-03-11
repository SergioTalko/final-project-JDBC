package service;

import controller.Session;
import dao.HotelDAO;
import entity.Hotel;
import entity.UserType;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        if (name == null) throw new NullPointerException("Input data is null");

        return hotelDAO.findHotelsByName(name);

    }


    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {
        if (city == null) throw new NullPointerException("Input data is null");

        return hotelDAO.findHotelsByCity(city);


    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        if (hotel == null) throw new NullPointerException("Input data is null");

        return hotelDAO.add(hotel);


    }

    public void deleteHotel(Hotel hotel) throws Exception {

        if (hotel != null)
            hotelDAO.delete(hotel.getId());


    }
}
