package application;

import java.util.List;

public interface TaetigkeitDAO {

	public List<Taetigkeit> getAllTaetigkeit();
	public void deleteTaetigkeit(int id);
	public void addTaetigkeit(String mitarbeiter, String projekt, int zeit, String beschreibung);
	public void updateTaetigkeit(int id, String mitarbeiter, String projekt, int zeit, String beschreibung);
}
