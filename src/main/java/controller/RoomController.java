package controller;


import dao.RoomDAO;
import entity.Filter;
import entity.Room;
import entity.UserType;
import service.RoomService;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

public class RoomController {

    private RoomService roomService = new RoomService();

    public Room addRoom(Room room) throws Exception {
        if (room == null) throw new NullPointerException("Input data is null");
        if (Session.getUserInSession() == null) throw new AccessDeniedException("You not login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
            roomService.addRoom(room);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }

        return roomService.addRoom(room);
    }

    public void deleteRoom(Room room) throws Exception {

        if (Session.getUserInSession() == null) throw new AccessDeniedException("You not login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN && room != null) {
            roomService.deleteRoom(room);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }
    }

    public ArrayList<Room> findRooms(Filter filter) throws Exception {

        if (Session.getUserInSession() != null) {
            return roomService.findRooms(filter);
        }
        throw new AccessDeniedException("Please login in system");
    }

}
