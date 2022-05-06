package mainmenujava;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Pham Dinh Phuc Ce140024
 */
public class DBManagement {
    public static final String URL = "jdbc:mysql://localhost:3306/user";
    public static final String UID = "root";
    public static final String PWD = "";
    private Connection conn = null;
    
    public DBManagement() throws SQLException{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        conn = (Connection)DriverManager.getConnection(URL,UID,PWD);
        
    }
    public Connection getConnected(){
        return this.conn;
    }
    public boolean isConnected(){
        return this.conn !=null;
    }
    public void closeConnection() throws SQLException{
        this.conn.close();
    }
}
