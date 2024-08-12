package hardCodedScripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadFromDataBase {

	public static void main(String[] args) throws SQLException {
//		//Step -1 Create Driver instance
//		Driver dbDriver = new Driver();
//		
//		// Step-2 Register to JDBC Driver
//		DriverManager.registerDriver(dbDriver);
//		
		// Step-3 Establish JDBC Connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium", "root", "root");
		
		//Step-4 Create Statement
		Statement statement = connection.createStatement();
		
		//Step-5 Execute Query to fetch data
		ResultSet result = statement.executeQuery("select * from student;");
		while(result.next()) {
			System.out.println(result.getInt("sid")+ "\t" +result.getString("sname")+"\t" +result.getString("phoneno")+"\t" +result.getString("course"));
		}
		//Step-6 Close database
		connection.close();		
	}
}
