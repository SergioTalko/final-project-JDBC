package service;

import controller.Session;
import dao.UserDAO;
import entity.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) throws Exception{
        if (user == null) throw new NullPointerException("Input data is null");
        return userDAO.add(user);
    }

    public void login(String name, String password) throws Exception {
        User user = userDAO.getObjectByColumnNameAndName("USER_NAME", name).get(0);

        if (name == null || password == null) throw new NullPointerException("Input data is null");


        if (user.getPassword().equals(password))
            Session.login(user);
    }
}
