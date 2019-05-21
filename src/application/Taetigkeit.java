package application;

public class Taetigkeit {

	private int id;
	private String mitar;
	private String proj;
	private int zeit;
	private String beschreibung;
	
	public Taetigkeit(int id, String mitar, String proj, int zeit, String beschreibung) {
		this.id = id;
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
