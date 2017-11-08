package server.security;

import server.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Authenticator {

    private Connection connection;
    public static List<Integer> sessionIds = new ArrayList<Integer>();

    public Authenticator() {
        this.connection = DBUtil.getConnection();
    }

    public boolean authenticateUser(String username, String password) {
        String passwordInDatabase = "";
        try {
            passwordInDatabase = DBUtil.getPasswordForUser(username, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password.equals(passwordInDatabase);
    }

    public static boolean authorizeRequest(int sessionId) {
        return sessionIds.contains(sessionId);
    }
}
