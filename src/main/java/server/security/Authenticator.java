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
    static Map<Integer, Map<String, Boolean>> sessionAccessRights = new HashMap<>();

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

    public boolean authorizeSingleRequest(String username, String resourceName) {
        Map<String, Boolean> userACL = null;
        try {
            userACL = DBUtil.getAclForUser(username, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userACL != null) {
            return userACL.get(resourceName);
        } else {
            return false;
        }
    }

    public void authorizeSession(String username, Integer sessionId) {
        Map<String, Boolean> userACL = null;
        try {
            userACL = DBUtil.getAclForUser(username, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sessionAccessRights.put(sessionId, userACL);
    }


    public static boolean authorizeRequest(int sessionId, String resourceName) {
        return sessionIds.containsKey(sessionId) && sessionAccessRights.get(sessionId).get(resourceName);
    }

    public static void removeSession(int sessionId) {
        sessionIds.remove(sessionId);
        sessionAccessRights.remove(sessionId);
    }

    public static String getUsernameForSessionId(int sessionId) {
        return sessionIds.get(sessionId);
    }
}
