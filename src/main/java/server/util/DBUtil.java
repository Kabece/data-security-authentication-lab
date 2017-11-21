package server.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/datasec";
            String dbUser = "root";
            String dbPassword = "root";
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String[] getPasswordForUser(String username, Connection conn) throws SQLException {
        String query = "SELECT password, salt FROM users WHERE username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        String[] passAndSalt = new String[2];
        passAndSalt[0] = rs.getString("password");
        passAndSalt[1] = rs.getString("salt");
        return passAndSalt;
    }

    public static Map<String, Boolean> getAclForUser(String username, Connection conn) throws SQLException {
        String query = "SELECT * FROM acl WHERE userid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Map<String, Boolean> userACL = new HashMap<String, Boolean>();
        userACL.put("print", rs.getInt("print") == 1);
        userACL.put("queue", rs.getInt("queue") == 1);
        userACL.put("topQueue", rs.getInt("top_queue") == 1);
        userACL.put("start", rs.getInt("start") == 1);
        userACL.put("stop", rs.getInt("stop") == 1);
        userACL.put("restart", rs.getInt("restart") == 1);
        userACL.put("status", rs.getInt("status") == 1);
        userACL.put("readConfig", rs.getInt("read_config") == 1);
        userACL.put("setConfig", rs.getInt("set_config") == 1);

        return userACL;
    }
}
