package app.model.dao;

import java.util.List;

import app.model.entity.Formation;
import app.model.entity.Theme;

public interface ThemeDao {
	public int add(Theme theme);
	public Integer addGetId(Theme theme);
	public boolean update(Theme theme);
	public boolean delete(Theme fromation);
	public Theme get(int id);
	public List<Theme> getAll();
	public List<Theme> getByName(String nom);
	public List<Theme> getSubThemesOf(Theme themeParent);
	public List<Theme> getByFormation(Formation formation);
	public List<Theme> getTreeRoots();
	public Theme getTreeRoot();
}
