package application;

import java.sql.Date;

public class Taetigkeit {

	private int id;
	private String mitar;
	private String proj;
	private int zeit;
	private String beschreibung;
	private Date beginn;
	
	public Taetigkeit(int id, Date beginn,String mitar, String proj, int zeit, String beschreibung) {
		this.id = id;
		this.beginn = beginn;
		this.mitar = mitar;
		this.proj = proj;
		this.zeit = zeit;
		this.beschreibung = beschreibung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBeginn() {
		return beginn;
	}

	public void setBeginn(Date beginn) {
		this.beginn = beginn;
	}

	public String getMitar() {
		return mitar;
	}

	public void setMitar(String mitar) {
		this.mitar = mitar;
	}

	public String getProj() {
		return proj;
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
}
