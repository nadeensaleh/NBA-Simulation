import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class NadeensConnection {
	public Connection getConnection(String username, String password) throws SQLException{
		//makes database connection via DriverManager
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@131.91.168.91:1521:r11g", username, password);
		return connection;
	}
	
	public Connection getConnection() throws SQLException {
		//makes database connection via DriverManager
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@131.91.168.91:1521:r11g", "nsaleh2012", "joebros333");
		return connection;
	}
}
