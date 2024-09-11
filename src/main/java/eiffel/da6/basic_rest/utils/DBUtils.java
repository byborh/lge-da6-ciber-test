package eiffel.da6.basic_rest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static Connection con;
    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/basicrest?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getDBConnection() {
        try {
            if (con ==null) {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                con = DriverManager.getConnection(dbUrl, "basic", "rest");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static String getStringFromRS(Object o) {
        if (o == null) {
            return "NULL";
        } else {
            return o.toString();
        }
    }
}
