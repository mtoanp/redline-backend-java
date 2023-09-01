package app.model.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
	private static final String URL = "jdbc:mysql://localhost:3306/redline";
	private static final String USER = "root";
	private static final String PASS = "root";

	private static Connection connection;

	private ConnectionDatabase() {}

	public static Connection getConnection() {
		if( connection == null) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				connection = DriverManager.getConnection(URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return connection;
	}
}
