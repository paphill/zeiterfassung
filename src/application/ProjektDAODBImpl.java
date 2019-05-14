package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjektDAODBImpl implements ProjektDAO {
	
	public List<Projekt> getAllProjekt(){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ArrayList<Projekt> projektliste = new ArrayList<>();
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT * from projekt");
			
			// 4. Process the result set
			while (myRs.next()) {
				Projekt proj = new Projekt(myRs.getInt("id"), myRs.getString("name"));
				projektliste.add(proj);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projektliste;
	}
	
	public void deleteProjekt(int id) {
		Connection myConn = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		String statement = "DELETE from projekt WHERE id=?";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.prepareStatement(statement);
			myStmt.setLong(1, id);
			myStmt.execute();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addProjekt(String name) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "INSERT INTO projekt (name) VALUES (?)";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, name);
			myStmt.execute();
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void updateProjekt(int id, String name) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "UPDATE `projekt` SET `name`=? WHERE id=?";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, name);
			myStmt.setInt(2, id);
			myStmt.execute();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
