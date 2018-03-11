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
            if (getObjectById(hotel.getId()) != null)
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


    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        if (name == null) throw new NullPointerException("Input data is null");

        ArrayList<Hotel> hotels = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement hotelStatement = connection.prepareStatement("SELECT * FROM  HOTELS WHERE HOTEL_NAME = ?") ) {
            hotelStatement.setString(1, name);
            ResultSet result = hotelStatement.executeQuery();
            while (result.next())
                hotels.add(createObjectFromDB(connection, result));
        } catch (SQLException e) {
            throw  new Exception("Cant find hotel with name " + name + "in DB with name HOTELS");
        }
        return hotels;

    }


    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {
        if (city == null) throw new NullPointerException("Input data is null");

        ArrayList<Hotel> hotels = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement hotelStatement = connection.prepareStatement("SELECT * FROM  HOTELS WHERE HOTEL_CITY = ?") ) {
            hotelStatement.setString(1, city);
            ResultSet result = hotelStatement.executeQuery();
            while (result.next())
                hotels.add(createObjectFromDB(connection, result));
        } catch (SQLException e) {
            throw  new Exception("Cant find hotel with city " + city + "in DB with name HOTELS");
        }
        return hotels;


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
