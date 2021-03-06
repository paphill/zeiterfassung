package application;

import java.sql.Date;

public class Taetigkeit {

	private int id;
	private String mitar;
	private String proj;
	private int zeit;
	private String beschreibung;
	private Date datum;
	private String border = " -";
	
	public Taetigkeit(int id, String mitar, String proj, int zeit, String beschreibung, Date datum) {
		this.id = id;
		this.mitar = mitar;
		this.proj = proj;
		this.zeit = zeit;
		this.beschreibung = beschreibung;
		this.datum = datum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMitar() {
		return mitar;
	}
	
	public String getMitarId() {
		String[] splitMitar;
		splitMitar = mitar.split(border);
		String mitarID = splitMitar[0];
		return mitarID;
	}

	public void setMitar(String mitar) {
		this.mitar = mitar;
	}

	public String getProj() {
		return proj;
	}
	
	public String getProjId() {
		String[] splitProj;
		splitProj = proj.split(border);
		String projID = splitProj[0];
		return projID;
	}

	public void setProj(String proj) {
		this.proj = proj;
	}

	public int getZeit() {
		return zeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public String toString() {
		return id + " / " + mitar + " / " + proj;
	}

	public String toCSV() {
		return "" + ";" +id + ";" + proj + ";" + zeit + ";" + beschreibung + ";" + datum;
	}
	
	public String toCSVProj() {
		return "" + ";" +id + ";" + mitar + ";" + zeit + ";" + beschreibung + ";" + datum;
	}
	
	public String toCSVMitar() {
		return "" + ";" +id + ";" + proj + ";" + zeit + ";" + beschreibung + ";" + datum;
	}
}
