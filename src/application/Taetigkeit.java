package application;

public class Taetigkeit {

	private int id;
	private Mitarbeiter mitar;
	private Projekt proj;
	
	public Taetigkeit(int id, Mitarbeiter mitar, Projekt proj) {
		this.id = id;
		this.mitar = mitar;
		this.proj = proj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mitarbeiter getMitar() {
		return mitar;
	}

	public void setMitar(Mitarbeiter mitar) {
		this.mitar = mitar;
	}

	public Projekt getProj() {
		return proj;
	}

	public void setProj(Projekt proj) {
		this.proj = proj;
	}
}
