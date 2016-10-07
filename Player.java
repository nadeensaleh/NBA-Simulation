import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public interface Player {
	public void printTable(Connection connection) throws SQLException;
	public void printNumberOfPlayers(Connection connection) throws SQLException;
	public int add(Connection connection, String name, String position, int draft_year, String education) throws SQLException;
	public void retire(Connection connection, String name, int retire_year) throws SQLException;
	public void retire(Connection connection, int ID, int retire_year) throws SQLException;
	public ArrayList<String> getCollection(Connection connection) throws SQLException;
	public void printCollection(ArrayList<String> al) throws SQLException;
}
