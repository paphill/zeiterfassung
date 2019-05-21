package application;

import java.sql.Date;
import java.util.List;

public interface TaetigkeitDAO {

	public List<Taetigkeit> getAllTaetigkeit();
	public void deleteTaetigkeit(int id);
	public void addTaetigkeit(Date beginn, String mitarbeiter, String projekt, int zeit, String beschreibung);
	public void updateTaetigkeit(int id, Date beginn, String mitarbeiter, String projekt, int zeit, String beschreibung);
}
