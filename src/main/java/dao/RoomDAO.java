package dao;


import entity.Room;


import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;

public class RoomDAO extends GeneralDAO<Room> {

    @Override
    Room add(Room room) throws Exception {
        return null;
    }

    @Override
    Room createObjectFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }

    @Override
    ArrayList<Room> createListObjectsFromDB(Connection connection, ResultSet result) throws Exception {
        return null;
    }
}
