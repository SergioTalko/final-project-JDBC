package service;

import controller.Session;
import dao.RoomDAO;
import entity.Filter;
import entity.Room;
import entity.UserType;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();

    public Room addRoom(Room room) throws Exception {
        if (room == null) throw new NullPointerException("Input data is null");

        return roomDAO.add(room);
    }

    public void deleteRoom(Room room) throws Exception {
        if (room != null) {
            roomDAO.delete(room.getId());
        }

    }

    public ArrayList<Room> findRooms(Filter filter) throws Exception {

        if (filter == null) throw new NullPointerException("Input data is null");
        ArrayList<Room> resultRooms = new ArrayList<>();
        for (Room room : roomDAO.getAll())
            if (filter.findRoomByFilter(room))
                resultRooms.add(room);
        return resultRooms;
    }
}
