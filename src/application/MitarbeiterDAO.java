package application;

import java.util.List;

public interface MitarbeiterDAO {

	public List<Mitarbeiter> getAllMitarbeiter();
	public void deleteMitarbeiter(int id);
	public void addMitarbeiter(String vorname, String nachname, String typ);
	public void updateMitarbeiter(int id, String vorname, String nachname, String typ);
}
