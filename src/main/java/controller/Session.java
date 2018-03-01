package controller;


import entity.User;

public class Session {

    private static User userInSession;


    public static void login(User user) {
        if (userInSession != null) {
            userInSession = user;
        }
    }

    public static void logout() {
        userInSession = null;
    }

    public static User getUserInSession() {
        return userInSession;
    }
}
