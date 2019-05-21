package application;

public class Projekt {

	private int id;
	private String name;
	private String auftraggeber;

	public Projekt(int id, String name, String auftraggeber) {
		this.id = id;
		this.name = name;
		this.auftraggeber = auftraggeber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuftraggeber() {
		return auftraggeber;
	}

	public void setAuftraggeber(String auftraggeber) {
		this.auftraggeber = auftraggeber;
	}

	public String toString() {
		return id + " - " + name;
	}
}
