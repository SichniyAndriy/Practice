package home.practice.util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final Properties properties;
    private static final String URL_DB = "jdbc:mysql://localhost:3306/test";

    static {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeException( "Can't load JDBC driver", e );
        }
        properties = new Properties();
        properties.setProperty( "user", "root" );
        properties.setProperty( "password", "password" );
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( URL_DB, properties );
    }
}
