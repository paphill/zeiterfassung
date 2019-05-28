package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class SQLSingleton {

	    private static SQLSingleton instance;
	    private Connection connection;

	    private SQLSingleton() throws SQLException {
	        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zeiterfassung", "root" , "");
	    }

	    public Connection getConnection() {
	        return connection;
	    }

	    public static SQLSingleton getInstance() throws SQLException {
	        if (instance == null) {
	            instance = new SQLSingleton();
	        } else if (instance.getConnection().isClosed()) {
	            instance = new SQLSingleton();
	        }

	        return instance;
	    }
}
