package demo;


import controller.UserController;
import dao.UserDAO;
import entity.User;

public class UserDemo {
    public static void main(String[] args) throws Exception {

        User user = new User("Test","hello","UA");
        UserDAO userDAO = new UserDAO();
        userDAO.add(user);

    }
}
