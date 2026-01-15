package dao;

import java.sql.*;
import db.DBConnection;

public class UserDAO {

    // returns role if login success, else null
    public static String login(String user, String pass) {

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getString("role");

        } catch (Exception e) {
            System.out.println("Login error");
        }
        return null;
    }

    // register ONLY USER
    public static boolean register(String user, String pass) {

        try {
            Connection con = DBConnection.getConnection();

            String check ="SELECT * FROM users WHERE username=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, user);

            if (ps1.executeQuery().next())
                return false;

            String insert = "INSERT INTO users VALUES (?, ?, 'USER')";
            PreparedStatement ps2 = con.prepareStatement(insert);
            ps2.setString(1, user);
            ps2.setString(2, pass);

            ps2.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Register error");
            return false;
        }
    }
}
