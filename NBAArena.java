import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class NBAArena extends Arena {
	
	//default constructor
	NBAArena() {
		arena_id = 0;
		aname = "";
		city = "";
	}
	
	//copy constructor
	NBAArena(NBAArena na){
		this.arena_id = na.arena_id;
		this.aname = na.aname; 
		this.city = na.city;
	}
	
	public void printTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from arena"); //get arena_id, aname, city
		System.out.format("%3s%20s%20s", "ID", "NAME", "CITY"); //table header
		System.out.print("\n");
		for(int i = 0; i < 43; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
		while (resultSet.next()) {
			arena_id = resultSet.getInt(1); //first parameter
			aname = resultSet.getString(2); //second parameter
			city = resultSet.getString(3); //third parameter
			System.out.format("%3d%20s%20s", arena_id, aname, city); //row data
			System.out.print("\n");
		}
		resultSet.close();
		statement.close();
	}
	
	public ArrayList<String> getCollection(Connection connection) throws SQLException {
		ArrayList<String> arrayList =  new ArrayList<String>(); //declares memory for ArrayList holding Strings
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from arena"); //get arena_id, aname, city
		while (resultSet.next()) {
			arena_id = resultSet.getInt(1); //first parameter
			aname = resultSet.getString(2); //second parameter
			city = resultSet.getString(3); //third parameter
			arrayList.add(String.format("%3d%20s%20s", arena_id, aname, city)); //row data
		}
		return arrayList; //returns arrayList to be printed in separate function
	}
	
	//getters and setters
	public int getArenaID() {
		return arena_id;
	}
	public String getAname() {
		return aname;
	}
	public String getCity() {
		return city;
	}
	
	public void setArenaID(int ArenaID) {
		this.arena_id = ArenaID;
	}
	public void setAname(String Aname) {
		this.aname = Aname;
	}
	public void setCity(String City) {
		this.city = City;
	}
		
	//variables
	protected int arena_id;
	protected String aname;
	protected String city;
}
