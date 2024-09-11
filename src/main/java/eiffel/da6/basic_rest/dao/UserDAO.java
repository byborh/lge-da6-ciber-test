package eiffel.da6.basic_rest.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eiffel.da6.basic_rest.dto.UserDTO;
import eiffel.da6.basic_rest.utils.DBUtils;
import eiffel.da6.basic_rest.utils.IdGenerator;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDAO {

    private SimpleDateFormat formatFr = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatUs = new SimpleDateFormat("yyyy-mm-dd");

    public List<UserDTO> getUsers() {
        Connection con = DBUtils.getDBConnection();
        ArrayList<UserDTO> users = new ArrayList<>();
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id, username, date_naiss FROM Users");
            while (rs.next()) {
                users.add(new UserDTO(rs.getInt(1), rs.getString(2), formatFr.parse(rs.getString(3), new ParsePosition(0))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public UserDTO getUserById(int id) {
        Connection con = DBUtils.getDBConnection();
        UserDTO user = null;
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id, username, date_naiss FROM Users WHERE id="+id);
            while (rs.next()) {
                user = new UserDTO(rs.getInt(1), rs.getString(2), formatFr.parse(rs.getString(3), new ParsePosition(0)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public UserDTO getUserByName(String username) {
        Connection con = DBUtils.getDBConnection();
        UserDTO user = null;
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id, username, date_naiss FROM Users WHERE username='"+username+"'");
            while (rs.next()) {
                user = new UserDTO(rs.getInt(1), rs.getString(2), formatFr.parse(rs.getString(3), new ParsePosition(0)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public int createUser(String username, String password, String date_naiss) {
        Connection con = DBUtils.getDBConnection();
        int response=-1;
        int id = IdGenerator.nextUserId();
        try {
            date_naiss = formatFr.format(formatUs.parse(date_naiss));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            response = stmt.executeUpdate("INSERT INTO Users(id, username, password, date_naiss) VALUES ("+id+",'"+username+"','"+password+"','"+ date_naiss+"');");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (response == 1) {
            return id;
        } else {
            return -1;
        }
    }

}
