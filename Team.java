import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Team {
	public void printNumberOfTeams(Connection connection) throws SQLException;
	public void printTeamTable(Connection connection) throws SQLException;
	public int add(Connection connection, String name, String city) throws SQLException;
	public void updateCity(Connection connection, String name, String newCity) throws SQLException;
	public void updateName(Connection connection, String name, String newName) throws SQLException;
	public void updateChampionships(Connection connection, String name) throws SQLException;
	public void remove(Connection connection, int id) throws SQLException;
	public ArrayList<String> getTeamCollection(Connection connection) throws SQLException;
	public void printTeamCollection(ArrayList<String> al) throws SQLException;
	
	//getters and setters
	public int getTeamID();
	public String getTname();
	public int getRank();
	public String getCity();
	public int getChampionships();
	
	public void setTeamID(int TeamID);
	public void setTname(String Tname);
	public void setRank(int Rank);
	public void setCity(String City);
	public void setChampionships(int Championships);	
}
