package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection conn;
        public Connection getConnection() throws ClassNotFoundException, SQLException
        {String connectionUrl =
                "jdbc:sqlserver://127.0.0.1:1433;"
                        + "database = profcom;"
                        + "user = pr;"
                        + "password = 123;"
                        + "encrypt = false;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);
            return conn;
        }
}
