package app.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.model.dao.FormationDao;
import app.model.dao.FormationDaoImplementation;
import app.model.dao.ThemeDao;
import app.model.dao.ThemeDaoImplementation;
import app.model.dbConnection.ConnectionDatabase;
import app.model.entity.Formation;
import app.model.entity.Theme;

@Service
public class ThemeServiceImplementation implements ThemeService {
	Connection connection;
	ThemeDao themeDao;
	FormationDao formationDao;
	
	
	public ThemeServiceImplementation() {
		connection = ConnectionDatabase.getConnection();
		themeDao = new ThemeDaoImplementation(connection);
		formationDao = new FormationDaoImplementation(connection);
	}

	@Override
	public int add(Theme theme) {
		return themeDao.add(theme);
	}

	@Override
	public Theme addGet(Theme theme) {
		Theme newTheme = null;
		Integer idTheme = themeDao.addGetId(theme);
		if(idTheme != null) {
			newTheme = themeDao.get(idTheme);
			if(theme.getFormations() != null && !theme.getFormations().isEmpty()) {
				for (Formation formation : theme.getFormations()) {
					addFormation(newTheme, formationDao.get(formation.getId()));
				}
			}
			return getWithDetails(idTheme);
		}
		return null;
	}

	@Override
	public boolean update(Theme theme) {
		if(get(theme.getId()) != null) {
			themeDao.update(theme);
			for (Formation formation : theme.getFormations()) {
				addFormation(theme, formationDao.get(formation.getId()));
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Theme theme) {
		return themeDao.delete(theme);
	}

	@Override
	public Theme get(int id) {
		return themeDao.get(id);
	}

	@Override
	public List<Theme> getAll() {
		return themeDao.getAll();
	}

	@Override
	public List<Theme> getByName(String nom) {
		return themeDao.getByName(nom);
	}

	@Override
	public List<Theme> getSubThemesOf(Theme themeParent) {
		return themeDao.getSubThemesOf(themeParent);
	}

	@Override
	public List<Theme> getByFormation(Formation formation) {
		return themeDao.getByFormation(formation);
	}

	@Override
	public List<Theme> getTreeRoots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Theme getWithDetails(int id) {
		Theme theme = this.themeDao.get(id);
		List<Theme> subThemes = this.themeDao.getSubThemesOf(theme);
		theme.setSubThemes(subThemes);
		List<Formation> formations = this.formationDao.getByTheme(theme);
		theme.setFormations(formations);
		return theme;
	}
	
	@Override
	public Theme getTreeRoot() {
		return builTreeNodsFrom(getWithDetails(themeDao.getTreeRoot().getId()));
	}
    
    public Theme builTreeNodsFrom(Theme theme) {
        if (theme.getSubThemes() != null || !theme.getSubThemes().isEmpty()) {
           List<Theme> subThemes = new ArrayList();
           for (Theme t : theme.getSubThemes()) {
               subThemes.add(this.builTreeNodsFrom(this.getWithDetails(t.getId())));
           }
           theme.setSubThemes(subThemes);
        }
        return theme;
    }
    
    
	@Override
	public boolean addFormation(Theme theme, Formation formation) {
		if(formationDao.addTheme(formation, theme))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean removeFormation(Theme theme, Formation formation) {
		if(formationDao.removeTheme(formation, theme))
			return true;
		else
			return false;
	}
	
	public boolean removeFormation(int idTheme, int idFormation) {
		Theme theme = get(idTheme);
		Formation formation = formationDao.get(idFormation);
		if(theme != null && formation != null) {
			if(formationDao.removeTheme(formation, theme))
				return true;
		}
		return false;
	}
}
