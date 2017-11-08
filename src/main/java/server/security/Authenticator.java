package server.security;

import server.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authenticator {

    private Connection connection;
    static Map<Integer, String> sessionIds = new HashMap<Integer, String>();

    public Authenticator() {
        this.connection = DBUtil.getConnection();
    }

    public boolean authenticateUser(String username, String password) {
        String passwordInDatabase = null;
        try {
            passwordInDatabase = DBUtil.getPasswordForUser(username, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password.equals(passwordInDatabase);
    }

    public static boolean authorizeRequest(int sessionId) {
        return sessionIds.containsKey(sessionId);
    }

    public static void removeSession(int sessionId) {
        sessionIds.remove(sessionId);
    }

    public static String getUsernameForSessionId(int sessionId) {
        return sessionIds.get(sessionId);
    }
}
