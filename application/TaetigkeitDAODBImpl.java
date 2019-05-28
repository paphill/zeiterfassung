package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaetigkeitDAODBImpl implements TaetigkeitDAO {
	
	public List<Taetigkeit> getAllTaetigkeit(){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT * from taetigkeit");
			
			// 4. Process the result set
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> getAllMitarTaetigkeit(String id){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE mitarid="+id);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> getAllDateTaetigkeit(String id){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE datum="+id);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> getAllSpecificTaetigkeit(String projid, String mitarid, String date){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE projid="+projid+" AND mitarid="+mitarid+" AND datum="+date);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> mitarProj(String projid, String mitarid){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE projid="+projid+" AND mitarid="+mitarid);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> projDate(String projid, String date){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE projid="+projid+" AND datum="+date);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> mitarDate(String mitarid, String date){
		Connection myConn = null;
		ResultSet myRs = null;
		Statement myStmt = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {		
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE mitarid="+mitarid+" AND datum="+date);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	public List<Taetigkeit> getAllProjTaetigkeit(String id){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ArrayList<Taetigkeit> taetigkeitliste = new ArrayList<>();
		try {
			myConn = SQLSingleton.getInstance().getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * from taetigkeit WHERE projid="+id);
			
			while (myRs.next()) {
				Taetigkeit taet = new Taetigkeit(myRs.getInt("id"), myRs.getString("mitarid"), myRs.getString("projid"), myRs.getInt("arbeitszeit"), myRs.getString("beschreibung"), myRs.getDate("datum"));
				taetigkeitliste.add(taet);
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
		return taetigkeitliste;
	}
	
	
	public void deleteTaetigkeit(int id) {
		Connection myConn = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		String statement = "DELETE from taetigkeit WHERE id=?";
		
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
	
	
	
	public void addTaetigkeit(String mitarbeiter, String projekt, int zeit, String beschreibung, Date datum) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "INSERT INTO taetigkeit (mitarid, projid, arbeitszeit, beschreibung, datum) VALUES (?,?,?,?,?)";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, mitarbeiter);
			myStmt.setString(2, projekt);
			myStmt.setInt(3, zeit);
			myStmt.setString(4, beschreibung);
			myStmt.setDate(5, datum);
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

	public void updateTaetigkeit(int id, String mitarbeiter, String projekt, int zeit, String beschreibung, Date datum) {
		PreparedStatement myStmt = null;
		Connection myConn = null;
		ResultSet myRs = null;
		String statement = "UPDATE `taetigkeit` SET `mitarid`=?, `projid`=?, `arbeitszeit`=?, `beschreibung`=?, `datum`=? WHERE id=?";
		
		try {
			// 1. Get a connection to database
			myConn = SQLSingleton.getInstance().getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(statement);
			myStmt.setString(1, mitarbeiter);
			myStmt.setString(2, projekt);
			myStmt.setInt(3, zeit);
			myStmt.setString(4, beschreibung);
			myStmt.setDate(5, datum);
			myStmt.setInt(6, id);
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
