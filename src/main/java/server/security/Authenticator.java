package server.security;

import server.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Authenticator {

    private Connection connection;

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
}
