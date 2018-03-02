package controller;

import dao.HotelDAO;
import entity.Hotel;
import exceptions.BadRequestException;

import java.util.ArrayList;


public class HotelController {


    private HotelDAO hotelDAO = new HotelDAO();

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {
        if (name == null)throw new NullPointerException("Input data is null");

        return hotelDAO.getObjectByColumnNameAndName("HOTEL_NAME", name);
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {
        if (city == null) throw new NullPointerException("Input data is null");

        return hotelDAO.getObjectByColumnNameAndName("HOTEL_CITY", city);

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
