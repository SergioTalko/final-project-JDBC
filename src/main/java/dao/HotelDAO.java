package dao;


import entity.Hotel;
import exceptions.BadRequestException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        super.setDbName("HOTELS");
    }



    public Hotel add(Hotel hotel) throws Exception {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO HOTELS VALUES (?, ?, ?, ?, ?)")) {
            if (getObjectById(connection, hotel.getId()) != null)
                throw new BadRequestException("Hotel with id " + hotel.getId() + " already exists in DB");

            statement.setLong(1, hotel.getId());
            statement.setString(2, hotel.getName());
            statement.setString(3, hotel.getCountry());
            statement.setString(4, hotel.getCity());
            statement.setString(5, hotel.getStreet());
            statement.executeQuery();
        } catch (SQLException e) {
            throw  new Exception("Cant save hotel with id " + hotel.getId() + " .Please try again later");
        }
        return hotel;
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
