import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleJDBC {
	public static void main(String[] args) {
		try {
			System.out.println("Getting a connection to database...");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:oracle:thin:@131.91.168.91:1521:r11g", "nsaleh2012", "joebros333");
			if (connection == null) {
				System .out.println("ERROR: Fail to make connection to database... Connection is null. Exiting.");
				System.exit(1);
			}
			else {
				System.out.println("Successfully obtaining a connection to the database.");
				connection.close();
			}
		}
		catch(SQLException se) {
			System.out.println("SQL EXCEPTION.");
			se.printStackTrace();
		}
	}
}
