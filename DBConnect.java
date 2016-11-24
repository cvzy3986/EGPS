import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnect {
	static void DBConnectFunc(Connection conn) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true", "team8",
					"password");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
		}
	}
}