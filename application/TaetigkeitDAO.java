package application;

import java.sql.Date;
import java.util.List;

public interface TaetigkeitDAO {

	public List<Taetigkeit> getAllTaetigkeit();
	public void deleteTaetigkeit(int id);
	public void addTaetigkeit(String mitarbeiter, String projekt, int zeit, String beschreibung, Date datum);
	public void updateTaetigkeit(int id, String mitarbeiter, String projekt, int zeit, String beschreibung, Date datum);
	public List<Taetigkeit> getAllMitarTaetigkeit(String id);
	public List<Taetigkeit> getAllProjTaetigkeit(String id);
	public List<Taetigkeit> getAllDateTaetigkeit(String id);
	public List<Taetigkeit> getAllSpecificTaetigkeit(String projid, String mitarid, String date);
	public List<Taetigkeit> mitarProj(String projid, String mitarid);
	public List<Taetigkeit> projDate(String projid, String date);
	public List<Taetigkeit> mitarDate(String mitarid, String date);
}
