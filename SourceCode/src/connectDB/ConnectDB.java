package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLHieuSach";
		String user = "sa";
		String ps = "123456";
		con = DriverManager.getConnection(url, user, ps);
	}
	public void disconnect() throws SQLException {
		if(con!=null)
			con.close();
	}
	public static Connection getConnection() {
		return con;
	}
}