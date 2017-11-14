package server.security;

import org.mindrot.jbcrypt.BCrypt;
import server.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {

    private Connection connection;
    static Map<Integer, String> sessionIds = new HashMap<Integer, String>();

    public Authenticator() {
        this.connection = DBUtil.getConnection();
    }

    public String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean authenticateUser(String username, String password) {
        String passwordInDatabase = null;
        String saltInDatabase = null;
        try {
            String[] dataFromDatabase = DBUtil.getPasswordForUser(username, connection);
            passwordInDatabase = dataFromDatabase[0];
            saltInDatabase = dataFromDatabase[1];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashPassword(password, saltInDatabase).equals(passwordInDatabase);
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
