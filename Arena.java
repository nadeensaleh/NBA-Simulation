import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;


public abstract class Arena {
	public void printNumberOfArenas(Connection connection) throws SQLException {
		int count = 0;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select count (*) from arena"); //get number of arenas
		while (resultSet.next()) {
			count = resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		System.out.println("Number of arenas: " + count);
	}
	
	public int add(Connection connection, String name, String city) throws SQLException {
		int id = 0;
		CallableStatement statement = connection.prepareCall("{call ? := ARENA_pkg.add(?, ?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		statement.setString(3, city);
		ResultSet resultSet = statement.executeQuery();
		id = statement.getInt(1);
		resultSet.close();
		statement.close();
		return id;
	}
	
	public void remove(Connection connection, String name) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := ARENA_pkg.remove(?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setString(2, name);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void remove(Connection connection, int id) throws SQLException {
		CallableStatement statement = connection.prepareCall("{call ? := ARENA_pkg.remove(?)}");
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.setInt(2, id);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
	}
	
	public void printCollection(ArrayList<String> al) throws SQLException {
		Iterator<String> itr = al.iterator();
		System.out.format("%3s%20s%20s", "ID", "NAME", "CITY"); //table header
		System.out.print("\n");
		for(int i = 0; i < 43; i++) {
			System.out.print("-");
		}
		System.out.print("\n");	
		
		//iterates through ArrayList
		while(itr.hasNext()){
			String result = itr.next();
			System.out.println(result); //displays each row (row data stored in result string)
		}
	}
}
