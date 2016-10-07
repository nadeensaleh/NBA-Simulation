import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class PlayerDriver {

	//class variables
	private static ArrayList<String> playerColl;
	
	public static void main(String[] args) {
		try {
			System.out.println("Getting a connection to database...");
			
			//creating an instance of NadeensConnection
			NadeensConnection c = new NadeensConnection();
			
			//use NadeensConnection as wrapper class to call method that returns connection object
			Connection connection = c.getConnection(); 
			
			if (connection == null) {
				System .out.println("ERROR: Fail to make connection to database... Connection is null. Exiting.");
				System.exit(1);
			}
			else {
				System.out.println("Successfully obtained a connection to the database.");
				
			}
			
			//declare an instance of the Player interface via NBAPlayer class object
			Player player =  new NBAPlayer();
			
			System.out.println("\nBefore add...");
			player.printTable(connection);
			player.printNumberOfPlayers(connection);
			int playerID_hill = player.add(connection, "Grant Hill", "Small forward", 1994, "Duke");
			int playerID_nash = player.add(connection,"Steve Nash","Point guard",1996,"Santa Clara");
			
			System.out.println("\nAfter add...");
			player.printTable(connection);
			player.printNumberOfPlayers(connection);
			
			//now let's retire some people
			player.retire(connection, "Grant Hill", 2013); //using first overloaded function (with name)
			player.retire(connection, playerID_nash, 2015); //using first overloaded function (with ID)
			
			//print the updated table via collection (in this case, ArrayList)
			System.out.println("\nAfter retire...");
			playerColl = player.getCollection(connection);
			player.printCollection(playerColl); 
			player.printNumberOfPlayers(connection);
			
			//checks if connection is closed, if not, closes it
			if(!connection.isClosed()) {
				connection.close();
				System.out.println("Closing the connection object...");
			}
			System.out.println("Connection is closed. Goodbye!");
		}
		catch(SQLException se) {
			System.out.println("SQL EXCEPTION.");
			se.printStackTrace();
		}
	}

}
