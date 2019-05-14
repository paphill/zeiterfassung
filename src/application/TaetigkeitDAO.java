package application;

import java.util.List;

public interface TaetigkeitDAO {

	public List<Taetigkeit> getAllTaetigkeit();
	public void deleteTaetigkeit(int id);
	public void addTaetigkeit(String mitarbeiter, String projekt);
	public void updateTaetigkeit(int id, String mitarbeiter, String projekt);
}
