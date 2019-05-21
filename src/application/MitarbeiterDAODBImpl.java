package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAODBImpl implements MitarbeiterDAO {
	
	public List<Mitarbeiter> getAllMitarbeiter(){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ArrayList<Mitarbeiter> mitarbeiterliste = new ArrayList<>();
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT * from mitarbeiter");
			
			// 4. Process the result set
			while (myRs.next()) {
				Mitarbeiter mitar = new Mitarbeiter(myRs.getInt("id"), myRs.getString("vorname"), myRs.getString("nachname"), myRs.getString("typ"));
				mitarbeiterliste.add(mitar);
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
		return mitarbeiterliste;
	}
	
	public void deleteMitarbeiter(int id) {
		Connection myConn = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		String statement = "DELETE from mitarbeiter WHERE id=?";
		
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
	
	public void addMitarbeiter(String vorname, String nachname, String typ) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "INSERT INTO mitarbeiter (vorname, nachname, typ) VALUES (?,?,?)";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, vorname);
			myStmt.setString(2, nachname);
			myStmt.setString(3, typ);
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

	public void updateMitarbeiter(int id, String vorname, String nachname, String typ) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "UPDATE `mitarbeiter` SET `vorname`=?,`nachname`=?, `typ`=? WHERE id=?";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, vorname);
			myStmt.setString(2, nachname);
			myStmt.setString(3, typ);
			myStmt.setInt(4, id);
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
