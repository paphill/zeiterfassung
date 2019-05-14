package application;

import java.util.List;

public interface ProjektDAO {

	public List<Projekt> getAllProjekt();
	public void deleteProjekt(int id);
	public void addProjekt(String name);
	public void updateProjekt(int id, String name);
}
