package eiffel.da6.basic_rest.utils;

import eiffel.da6.basic_rest.dto.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParsePosition;

public class IdGenerator {

    public static IdGenerator generator = new IdGenerator();
    private static int userId;

    private IdGenerator() {
        Connection con = DBUtils.getDBConnection();
        Integer id = null;
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM Users");
            rs.next();
            this.userId = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int nextUserId() {
        userId += 1;
        return userId;
    }
}
