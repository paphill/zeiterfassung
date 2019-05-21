package application;

import java.util.List;

public interface ProjektDAO {

	public List<Projekt> getAllProjekt();
	public void deleteProjekt(int id);
	public void addProjekt(String name, String auftraggeber);
	public void updateProjekt(int id, String name, String auftraggeber);
}
