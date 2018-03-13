package demo;


import controller.UserController;
import dao.UserDAO;
import entity.User;
import entity.UserType;

public class UserDemo {
    public static void main(String[] args) throws Exception {

        User user = new User("Test2","hello","UA", UserType.ADMIN);
        UserDAO userDAO = new UserDAO();
        userDAO.add(user);

    }
}
