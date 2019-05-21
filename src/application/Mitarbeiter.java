package application;

public class Mitarbeiter {
	
	private int id;
	private String vorname;
	private String nachname;
	private String typ;
	
	public Mitarbeiter(int id, String vorname, String nachname, String typ) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.typ = typ;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public String toString() {
		return id + " - " + vorname + " " + nachname;
	}
}
