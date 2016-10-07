import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Iterator;

public class NBATeam implements Team {
	
	//default constructor
	NBATeam() {
		team_id = 0;
		tname = "";
		rank = 0;
		city = "";
		championships = 0;
	}
	
	//copy constructor
	NBATeam(NBATeam nt){
		this.team_id = nt.team_id;
		this.tname = nt.tname;
		this.rank = nt.rank;
		this.city = nt.city;
		this.championships = nt.championships;
	}
	
	public void printNumberOfTeams(Connection connection) throws SQLException {
		int count = 0;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select count (*) from team"); //get number of teams
		while (resultSet.next()) {
			count = resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		System.out.println("Number of teams: " + count);
	}
	
	public void printTeamTable(Connection connection) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from team"); //get team_id, tname, rank, city, championships
		System.out.format("%3s%20s%20s%20s%20s", "ID", "NAME", "RANK", "CITY", "CHAMPIONSHIPS"); //table header
		System.out.print("\n");
		for(int i = 0; i < 83; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
		while (resultSet.next()) {
			team_id = resultSet.getInt(1); //first parameter
			tname = resultSet.getString(2); //second parameter
			rank = resultSet.getInt(3); //third parameter
			city = resultSet.getString(4); //fourth parameter
			championships = resultSet.getInt(5); //fifth parameter
			System.out.format("%3d%20s%20d%20s%20d", team_id, tname, rank, city, championships); //row data
			System.out.print("\n");
		}
		resultSet.close();
		statement.close();
	}
	
	public int add(Connection connection, String name, String city) throws SQLException {
		int id = 0;
		CallableStatement statement = connection.prepareCall("{call ? := TEAM_pkg.add(?, ?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setString(3, city);
		statement.setInt(4, id); //OUT parameter
		ResultSet resultSet = statement.executeQuery();
		id = statement.getInt(1);
		resultSet.close();
		statement.close();
		return id;
	}
	
	public void updateCity(Connection connection, String name, String newCity) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := TEAM_pkg.updateCity(?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setString(3, newCity);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void updateName(Connection connection, String name, String newName) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := TEAM_pkg.updateName(?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setString(3, newName);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void updateChampionships(Connection connection, String name) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := TEAM_pkg.updateChampionships(?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void remove(Connection connection, int id) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := TEAM_pkg.remove(?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setInt(2, id);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public ArrayList<String> getTeamCollection(Connection connection) throws SQLException {
		ArrayList<String> arrayList =  new ArrayList<String>(); //declares memory for ArrayList holding Strings
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from team"); //get team_id, tname, rank, city, championships
		while (resultSet.next()) {
			team_id = resultSet.getInt(1); //first parameter
			tname = resultSet.getString(2); //second parameter
			rank = resultSet.getInt(3); //third parameter
			city = resultSet.getString(4); //fourth parameter
			championships = resultSet.getInt(5); //fifth parameter
			arrayList.add(String.format("%3d%20s%20d%20s%20d", team_id, tname, rank, city, championships)); //row data
		}
		return arrayList; //returns arrayList to be printed in separate function
	}

	public void printTeamCollection(ArrayList<String> al) throws SQLException {
		Iterator<String> itr = al.iterator();
		System.out.format("%3s%20s%20s%20s%20s", "ID", "NAME", "RANK", "CITY", "CHAMPIONSHIPS"); //table header
		System.out.print("\n");
		for(int i = 0; i < 83; i++) {
			System.out.print("-");
		}
		System.out.print("\n");		
		
		//iterates through ArrayList
		while(itr.hasNext()){
			String result = itr.next();
			System.out.println(result); //displays each row (row data stored in result string)
		}
	}
	
	//getters and setters
	public int getTeamID() {
		return team_id;
	}
	public String getTname() {
		return tname;
	}
	public int getRank() {
		return rank;
	}
	public String getCity() {
		return city;
	}
	public int getChampionships() {
		return championships;
	}
	
	public void setTeamID(int TeamID) {
		this.team_id = TeamID;
	}
	public void setTname(String Tname) {
		this.tname = Tname;
	}
	public void setRank(int Rank) {
		this.rank = Rank;
	}
	public void setCity(String City) {
		this.city = City;
	}
	public void setChampionships(int Championships) {
		this.championships = Championships;
	}

	//variables
	protected int team_id;
	protected String tname;
	protected int rank;
	protected String city;
	protected int championships;
}
