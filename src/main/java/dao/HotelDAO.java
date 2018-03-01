package dao;


import entity.Hotel;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        super.setDbName("HOTELS");
    }



    @Override
    Hotel add(Hotel hotel) throws Exception {
        return null;
    }

    @Override
    Hotel createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        long id = result.getLong(1);
        String hotelName = result.getString(2);
        String country = result.getString(3);
        String city = result.getString(4);
        String street = result.getString(5);
        Hotel hotel = new Hotel(hotelName, country, city, street);
        hotel.setId(id);
        return hotel;
    }

    @Override
    ArrayList<Hotel> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        ArrayList<Hotel> hotels = new ArrayList<>();
        while (result.next())
            hotels.add(createObjectFromDB(connection, result));
        return hotels;
    }
}
