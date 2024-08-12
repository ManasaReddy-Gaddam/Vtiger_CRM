package hardCodedScripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyDataBase {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium", "root", "root");
		
		// Create Statement
		Statement statement = connection.createStatement();
		
		// Execute Query to fetch data
		int result = statement.executeUpdate("insert into student(sid, sname, phoneno, course) values(106,\"Rama\",\"9876543201\",\"Advance\")");
		System.out.println(result);
		connection.close();
	}
}