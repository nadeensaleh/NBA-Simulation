import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeamDriver {

	public static void main(String[] args) {
		try {
			System.out.println("Getting a connection to database...");
			
			//creating an instance of NadeensConnection
			NadeensConnection c = new NadeensConnection();
			
			//use NadeensConnection as wrapper class to call method that returns connection object
			Connection connection = c.getConnection("nsaleh2012", "joebros333"); 
			
			if (connection == null) {
				System .out.println("ERROR: Fail to make connection to database... Connection is null. Exiting.");
				System.exit(1);
			}
			else {
				System.out.println("Successfully obtained a connection to the database.");
				
			}
			
			//declare an instance of the Team interface via NBATeam class object
			Team team =  new NBATeam();
			
			System.out.println("\nBefore add...");
			team.printTeamTable(connection); //prints teams
			team.printNumberOfTeams(connection); //and prints number of teams
			int teamID_jazz = team.add(connection, "Jazz", "Utah");
			int teamID_raptors = team.add(connection, "Raptors", "Toronto");
			
			System.out.println("\nAfter add...");
			team.printTeamTable(connection); //prints teams
			team.printNumberOfTeams(connection); //and prints number of teams
			
			//updates to team table
			team.updateCity(connection, "Clippers", "Los Angles"); 
			team.updateName(connection, "Blazers", "Trail Blazers");
			team.updateChampionships(connection, "Mavericks");
			
			System.out.println("\nAfter update...");
			team.printTeamTable(connection); //prints teams
			
			//remove a team using id
			team.remove(connection, teamID_jazz);
			
			System.out.println("\nAfter remove...");
			
			//print the updated tables via collection (in this case, ArrayList)
			ArrayList<String> TeamColl = team.getTeamCollection(connection);
			team.printTeamCollection(TeamColl);
			team.printNumberOfTeams(connection); //and prints number of teams
			
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
