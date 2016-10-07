import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class ArenaDriver {

	//class variables
	private static ArrayList<String> arenaColl;
		
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
			
			//declare an instance of the NBAArena class
			NBAArena arena =  new NBAArena();
			
			System.out.println("\nBefore add...");
			arena.printTable(connection);
			arena.printNumberOfArenas(connection);
			int arenaID_oracle = arena.add(connection, "Oracle Arena", "Oakland"); 
			int arenaID_pepsi = arena.add(connection, "Pepsi Center", "Denver");
			int arenaID_toyota = arena.add(connection, "Toyota Center", "Houston");
			
			System.out.println("\nAfter add...");
			arena.printTable(connection);
			arena.printNumberOfArenas(connection);
			
			
			//now let's remove some arenas
			arena.remove(connection, "Oracle Arena"); 
			arena.remove(connection, arenaID_pepsi);
			arena.remove(connection, arenaID_toyota);
			
			//print the updated table via collection (in this case, ArrayList)
			System.out.println("\nAfter remove...");
			arenaColl = arena.getCollection(connection);
			arena.printCollection(arenaColl); 
			arena.printNumberOfArenas(connection);
			
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
