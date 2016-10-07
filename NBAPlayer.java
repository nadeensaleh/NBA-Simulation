import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;


public class NBAPlayer implements Player {
	
	//default constructor
	NBAPlayer() {
		player_id = 0;
		pname = "";
		position = "";
		draft_year = 0;
		retire_year = 0;
		education = "";
	}
	
	//copy constructor
	NBAPlayer(NBAPlayer np){
		this.player_id = np.player_id;
		this.pname = np.pname;
		this.position = np.position;
		this.draft_year = np.draft_year;
		this.retire_year = np.retire_year;
		this.education = np.education;
	}
		
	public void printTable(Connection connection) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from player"); //get player_id, pname, position, draft_year, retire_year, education
		System.out.format("%3s%20s%20s%20s%20s%20s", "ID", "NAME", "POSITION", "DRAFT YEAR", "RETIRE YEAR", "EDUCATION"); //table header
		System.out.print("\n");
		for(int i = 0; i < 103; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
		while (resultSet.next()) {
			player_id = resultSet.getInt(1); //first parameter
			pname = resultSet.getString(2); //second parameter
			position = resultSet.getString(3); //third parameter
			draft_year = resultSet.getInt(4); //fourth parameter
			retire_year = resultSet.getInt(5); //fifth parameter
			education = resultSet.getString(6); //sixth parameter
			System.out.format("%3d%20s%20s%20d%20d%20s", player_id, pname, position, draft_year, retire_year, education); //row data
			System.out.print("\n");
		}
		resultSet.close();
		statement.close();
	}
	
	public void printNumberOfPlayers(Connection connection) throws SQLException {
		int count = 0;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select count (*) from player"); //get number of players
		while (resultSet.next()) {
			count = resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		System.out.println("Number of players: " + count);
	}
	
	public int add(Connection connection, String name, String position, int draft_year, String education) throws SQLException {
		int id = 0;
		CallableStatement statement = connection.prepareCall("{call ? := PLAYER_pkg.add(?, ?, ?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setString(3, position);
		statement.setInt(4, draft_year);
		statement.setString(5, education);
		ResultSet resultSet = statement.executeQuery();
		id = statement.getInt(1);
		resultSet.close();
		statement.close();
		return id;
	}
	
	public void retire(Connection connection, String name, int retire_year) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := PLAYER_pkg.retire(?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setInt(3, retire_year);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void retire(Connection connection, int ID, int retire_year) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := PLAYER_pkg.retire(?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setInt(2, ID);
		statement.setInt(3, retire_year);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public ArrayList<String> getCollection(Connection connection) throws SQLException {
		ArrayList<String> arrayList =  new ArrayList<String>(); //declares memory for ArrayList holding Strings
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from player"); //get player_id, pname, position, draft_year, retire_year, education
		while (resultSet.next()) {
			player_id = resultSet.getInt(1); //first parameter
			pname = resultSet.getString(2); //second parameter
			position = resultSet.getString(3); //third parameter
			draft_year = resultSet.getInt(4); //fourth parameter
			retire_year = resultSet.getInt(5); //fifth parameter
			education = resultSet.getString(6); //sixth parameter
			arrayList.add(String.format("%3d%20s%20s%20d%20d%20s", player_id, pname, position, draft_year, retire_year, education)); //row data
		}
		return arrayList; //returns arrayList to be printed in separate function
	}

	public void printCollection(ArrayList<String> al) throws SQLException {
		Iterator<String> itr = al.iterator();
		System.out.format("%3s%20s%20s%20s%20s%20s", "ID", "NAME", "POSITION", "DRAFT YEAR", "RETIRE YEAR", "EDUCATION"); //table header
		System.out.print("\n");
		for(int i = 0; i < 103; i++) {
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
	public int getPlayerID() {
		return player_id;
	}
	public String getPname() {
		return pname;
	}
	public String getPosition() {
		return position;
	}
	public int getDraftYear() {
		return draft_year;
	}
	public int getRetireYear() {
		return retire_year;
	}
	public String getEduction() {
		return education;
	}
	
	public void setPlayerID(int PlayerID) {
		this.player_id = PlayerID;
	}
	public void setPname(String Pname) {
		this.pname = Pname;
	}
	public void setPositon(String Position) {
		this.position = Position;
	}
	public void setDraftYear(int Draft_Year) {
		this.draft_year = Draft_Year;
	}
	public void setRetireYear(int Retire_Year) {
		this.retire_year = Retire_Year;
	}
	public void setEducation(String Education) {
		this.education = Education;
	}
		
	//variables
	protected int player_id;
	protected String pname;
	protected String position;
	protected int draft_year;
	protected int retire_year;
	protected String education;
}
