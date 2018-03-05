package controller;

import dao.HotelDAO;
import entity.Hotel;
import entity.UserType;
import exceptions.BadRequestException;
import service.HotelService;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;


public class HotelController {


    private HotelService hotelService = new HotelService();

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        if (name == null) throw new NullPointerException("Input data is null");

        if (Session.getUserInSession() != null) {
            return hotelService.findHotelsByName(name);
        } else
            throw new AccessDeniedException("Please login in system");
    }


    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {
        if (city == null) throw new NullPointerException("Input data is null");

        if (Session.getUserInSession() != null) {
            return hotelService.findHotelsByCity(city);
        } else
            throw new AccessDeniedException("Please login in system");


    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        if (hotel == null) throw new NullPointerException("Input data is null");

        if (Session.getUserInSession() == null) throw new AccessDeniedException("You not login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
            return hotelService.addHotel(hotel);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }
    }

    public void deleteHotel(Hotel hotel) throws Exception {

        if (Session.getUserInSession() == null) throw new AccessDeniedException("You not login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN && hotel != null) {
            hotelService.deleteHotel(hotel);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }
    }


}

