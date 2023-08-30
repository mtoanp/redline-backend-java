package app.service;

import java.util.List;

import app.model.entity.Formation;
import app.model.entity.Theme;

public interface ThemeService {
	public int add(Theme theme);
	public Theme addGet(Theme theme);
	public boolean update(Theme theme);
	public boolean delete(Theme fromation);
	public Theme get(int id);
	public Theme getWithDetails(int id);
	public List<Theme> getAll();
	public List<Theme> getByName(String nom);
	public List<Theme> getSubThemesOf(Theme themeParent);
	public List<Theme> getByFormation(Formation formation);
	public List<Theme> getTreeRoots();
	public Theme getTreeRoot();
	public boolean addFormation(Theme theme, Formation formation);
	public boolean removeFormation(Theme theme, Formation formation);
	public boolean removeFormation(int idTheme, int idFormation);
}
