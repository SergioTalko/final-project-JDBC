package dao;


import entity.Room;
import entity.Hotel;
import exceptions.BadRequestException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RoomDAO extends GeneralDAO<Room> {

    private HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super.setDbName("ROOMS");
    }

    public Room add(Room room) throws Exception {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ROOMS VALUES (?, ?, ?, ?, ?, ?, ?)")){

            if (getObjectById(room.getId()) != null)
                throw new BadRequestException("Room with id " + room.getId() + "already exists");

            statement.setLong(1, room.getId());
            statement.setInt(2, room.getNumberOfGuests());
            statement.setDouble(3, room.getPrice());
            statement.setString(4, String.valueOf(room.isBreakfastIncluded()));
            statement.setString(5, String.valueOf(room.isPetsAllowed()));
            statement.setDate(6, new java.sql.Date(room.getDateAvailableFrom().getTime()));
            statement.setLong(7, room.getHotel().getId());
            statement.executeQuery();
        } catch (SQLException e) {
            throw  new Exception("Cant save room with id " + room.getId() + ".Try again later");
        }
        return room;
    }

    public Room update(Room room) throws Exception {
        if (room == null) throw new NullPointerException("Cant update null");

        if (getObjectById(room.getId()) == null) throw new Exception("Cant find room with id " + room.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE ROOMS SET  DATE_FROM = ?  WHERE ID = ?")) {


            statement.setDate(1, new java.sql.Date(room.getDateAvailableFrom().getTime()));
            statement.setLong(2, room.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update room with id " + room.getId() + " try again later.");
            e.printStackTrace();
        }
        return room;
    }
    public Room update(Connection connection , Room room) throws Exception {
        if (room == null) throw new NullPointerException("Cant update null");

        if (getObjectById(room.getId()) == null) throw new Exception("Cant find room with id " + room.getId());

        try (
                PreparedStatement statement = connection.prepareStatement("UPDATE ROOMS SET  DATE_FROM = ?  WHERE ID = ?")) {


            statement.setDate(1, new java.sql.Date(room.getDateAvailableFrom().getTime()));
            statement.setLong(2, room.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update room with id " + room.getId() + " try again later.");
            e.printStackTrace();
        }
        return room;
    }

    @Override
    Room createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        long roomId = result.getLong(1);
        int guests = result.getInt(2);
        double price = result.getDouble(3);
        boolean breakfast = Boolean.valueOf(result.getString(4));
        boolean pets = Boolean.valueOf(result.getString(5));
        Date dateFrom = new Date(result.getDate(6).getTime());
        long hotelId = result.getLong(7);
        Room room = new Room(guests, price, breakfast, pets, dateFrom, hotelDAO.getObjectById(hotelId));
        room.setId(roomId);
        return room;
    }

    @Override
  public   ArrayList<Room> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Hotel> hotels = hotelDAO.getAll();
        while (result.next()) {
            rooms.add(createObjectFromDB(connection, result));
        }
        return rooms;
    }
}
